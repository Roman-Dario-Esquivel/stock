package com.stocktienda.stock.service.implementations;

import com.stocktienda.stock.ModelsAuxiliary.ReservationDataList;
import com.stocktienda.stock.dtos.dtoCustomer;
import com.stocktienda.stock.dtos.dtoReservation;
import com.stocktienda.stock.dtos.dtoReservationCustomer;
import com.stocktienda.stock.exception.CustomException;
import com.stocktienda.stock.models.Customer;
import com.stocktienda.stock.models.Products;
import com.stocktienda.stock.models.Reservation;
import com.stocktienda.stock.repositorys.IReservationRepository;
import com.stocktienda.stock.service.interfaces.ICustomerService;
import com.stocktienda.stock.service.interfaces.IProductsService;
import com.stocktienda.stock.service.interfaces.IReservationService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ReservationService implements IReservationService {

    private final IReservationRepository reservationRepository;

    private final ICustomerService customerService;

    private final IProductsService productsService;

    public ReservationService(IReservationRepository reservationRepository, com.stocktienda.stock.service.interfaces.ICustomerService customerService, com.stocktienda.stock.service.interfaces.IProductsService productsService) {
        this.reservationRepository = reservationRepository;
        this.customerService = customerService;
        this.productsService = productsService;
    }

    @Override
    public Reservation getOneReservation(Long id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        Reservation reservation = optionalReservation
                .orElseThrow(() -> new RuntimeException("El cliente no esta cargado ni tiene reservas"));
        return reservation;
    }

    @Override
    public List<ReservationDataList> listAllReservationActive() {
        List<Reservation> list = reservationRepository.findAllWithoutActive();
        List<ReservationDataList> listAux = new ArrayList<>();

        for (Reservation reserva : list) {
            ReservationDataList data = new ReservationDataList();
            data.setArticulo(reserva.getProduct().getDescription());
            data.setBalance(reserva.getBalance());
            data.setCodigo(reserva.getProduct().getIdProduct());
            data.setDeposit(reserva.getDeposit());
            data.setDni(reserva.getCustomer().getDni());
            data.setId(reserva.getId());
            data.setName(reserva.getCustomer().getName());
            data.setNumberMobile(reserva.getCustomer().getNumberMobile());
            data.setPrice(reserva.getPrice());
            data.setQuantity(reserva.getQuantity());
            listAux.add(data);
        }
        return listAux;
    }

    @Override
    public boolean saveCustomerReservation(dtoReservationCustomer newReservation) {
        Products products = productsService.getOneProducts(newReservation.getCode());
        dtoCustomer customer = new dtoCustomer();
        customer.setDni(newReservation.getDni());
        customer.setName(newReservation.getName());
        customer.setNumberMobile(newReservation.getNumberMobile());
        boolean customers = customerService.saveCustomer(customer);
        if (!customers) {
            throw new CustomException("No se guardo cliente");
        }
        Customer custom = customerService.getOneCustomer(newReservation.getDni());
        Reservation reserva = Reservation.builder()
                .active(true)
                .balance((products.getPrice() * newReservation.getQuantity()) - newReservation.getDeposit())
                .deposit(newReservation.getDeposit())
                .price(products.getPrice() * newReservation.getQuantity())
                .customer(custom)
                .quantity(newReservation.getQuantity())
                .product(products)
                .build();

        Reservation saveReserva = reservationRepository.save(reserva);
        if (saveReserva != null) {
            productsService.createReserva(products.getIdProduct(), saveReserva.getQuantity());
            customerService.addCreditsEarned(reserva.getCustomer().getDni());
        }

        return saveReserva != null;
    }

    @Override
    public boolean saveReserva(dtoReservation newReservation) {
        Products products = productsService.getOneProducts(newReservation.getCode());
        Customer custom = customerService.getOneCustomer(newReservation.getDni());
        Reservation reserva = Reservation.builder()
                .active(true)
                .balance((products.getPrice() * newReservation.getQuantity()) - newReservation.getDeposit())
                .deposit(newReservation.getDeposit())
                .price(products.getPrice() * newReservation.getQuantity())
                .customer(custom)
                .quantity(newReservation.getQuantity())
                .product(products)
                .build();

        Reservation saveReserva = reservationRepository.save(reserva);
        if (saveReserva != null) {
            productsService.createReserva(products.getIdProduct(), saveReserva.getQuantity());
            customerService.addCreditsEarned(reserva.getCustomer().getDni());
        }
        return saveReserva != null;
    }

    @Override
    public String increaseDeposit(Long id, double deposit) {
        Reservation reservation = getOneReservation(id);
        reservation.setDeposit(reservation.getDeposit() + deposit);
        reservation.setBalance(reservation.getPrice()-reservation.getDeposit());
        Reservation saveReserva = reservationRepository.save(reservation);
        if (reservation.getDeposit() >= reservation.getPrice()) {
            productsService.salesReserva(reservation.getId(),reservation.getQuantity());
            customerService.addCreditsCompleted(id);
            customerService.calculateConfidence(reservation.getCustomer().getDni());
            double vuelto = reservation.getDeposit() - reservation.getPrice();
            return "Se entrega el producto con codigo " + reservation.getProduct().getIdProduct() + " al completar el pago.El vuelto a dar es :" + vuelto;
        } else{
            return "Le queda pendiente un saldo de :" + reservation.getBalance();
        }

    }

    @Override
    public boolean salesReserva(Long id) {
        Reservation reservation = getOneReservation(id);
        reservation.setDeposit(reservation.getPrice());
        reservation.setBalance(0);
        Reservation saveReserva = reservationRepository.save(reservation);
        productsService.salesReserva(reservation.getId(),reservation.getQuantity());
        customerService.addCreditsCompleted(reservation.getCustomer().getDni());
        customerService.calculateConfidence(reservation.getCustomer().getDni());
        
        return saveReserva != null;
    }

    @Override
    public boolean cancelReserva(Long id) {
        Reservation reservation = getOneReservation(id);
        reservation.setActive(false);
        Reservation saveReserva = reservationRepository.save(reservation);
        productsService.cancelReserva(reservation.getProduct().getIdProduct(), reservation.getQuantity());
        return saveReserva != null;
    }
}
