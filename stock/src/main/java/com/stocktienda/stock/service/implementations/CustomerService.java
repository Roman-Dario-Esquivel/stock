package com.stocktienda.stock.service.implementations;

import com.stocktienda.stock.ModelsAuxiliary.ReservationCustomer;
import com.stocktienda.stock.dtos.dtoCustomer;
import com.stocktienda.stock.models.Customer;
import com.stocktienda.stock.models.Reservation;
import com.stocktienda.stock.repositorys.ICustomerRepository;
import com.stocktienda.stock.service.interfaces.ICustomerService;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getOneCustomer(Long dni) {
        Optional<Customer> optionalCustomer = customerRepository.findByDni(dni);
        Customer customer = optionalCustomer
                .orElseThrow(() -> new RuntimeException("El cliente no esta cargado ni tiene reservas"));
        return customer;
    }

     @Override
    public List<ReservationCustomer> listDni(Long dni) {
        Customer customer = this.getOneCustomer(dni);
        List<ReservationCustomer> reservationcustomer = new ArrayList<>();
        String name = customer.getName();
        for (Reservation reservation : customer.getReservations()) {
            if(reservation.isActive()){
                ReservationCustomer auxdata = new ReservationCustomer();
            auxdata.setArticulo(reservation.getProduct().getDescription());
            auxdata.setBalance(reservation.getBalance());
            auxdata.setQuantity(reservation.getQuantity());
            auxdata.setCodigo(reservation.getProduct().getIdProduct());
            auxdata.setPrice(reservation.getPrice());
            auxdata.setDeposit(reservation.getDeposit());
            auxdata.setName(name);
            auxdata.setId(reservation.getId());
            reservationcustomer.add(auxdata);
            }
        }

        return reservationcustomer;
    }
    
    @Override
    public String verifyExistence(Long dni) {
        return customerRepository.findByDni(dni)
                .map(Customer::getName)
                .orElse("false");

}/*
         Optional<CustomerData> optionalCustomer = customerRepository.findByDniCustomer(dni);
        if(optionalCustomer.isPresent()){
            CustomerData  customer =optionalCustomer.get();
            return customer.getName();
        }
        else{
            return "false";
        }
        */
         


    @Override
    public boolean saveCustomer(dtoCustomer dtocustomer) {

        Customer customer = Customer.builder()
                .dni(dtocustomer.getDni())
                .name(dtocustomer.getName().toLowerCase())
                .numberMobile(dtocustomer.getNumberMobile())
                .confidence(0)
                .creditsCompleted(0)
                .creditsEarned(0)
                .build();
        Customer saveCustomer = customerRepository.save(customer);

        return saveCustomer != null;
    }

    @Override
    public boolean editCustomer(dtoCustomer dtocustomer) {

        Optional<Customer> optionalCustomer = customerRepository.findByDni(dtocustomer.getDni());

        Customer customer = optionalCustomer
                .orElseThrow(() -> new RuntimeException("Cliente no existe"));
        customer.setDni(dtocustomer.getDni());
        customer.setName(dtocustomer.getName());
        customer.setNumberMobile(dtocustomer.getNumberMobile());
        Customer saveCustomer = customerRepository.save(customer);

        return saveCustomer != null;

    }

    @Override
    public boolean addCreditsEarned(Long dni) {

        Optional<Customer> optionalCustomer = customerRepository.findByDni(dni);

        Customer customer = optionalCustomer
                .orElseThrow(() -> new RuntimeException("Cliente no existe"));
        customer.setCreditsEarned(customer.getCreditsEarned() + 1);
        Customer saveCustomer = customerRepository.save(customer);

        return saveCustomer != null;

    }

    @Override
    public boolean addCreditsCompleted(Long dni) {

        Optional<Customer> optionalCustomer = customerRepository.findByDni(dni);

        Customer customer = optionalCustomer
                .orElseThrow(() -> new RuntimeException("Cliente no existe"));
        customer.setCreditsCompleted(customer.getCreditsCompleted() + 1);
        Customer saveCustomer = customerRepository.save(customer);

        return saveCustomer != null;

    }


    @Override
    public boolean calculateConfidence(Long dni) {

        Optional<Customer> optionalCustomer = customerRepository.findByDni(dni);

        Customer customer = optionalCustomer
                .orElseThrow(() -> new RuntimeException("Cliente no existe"));
        DecimalFormat df = new DecimalFormat("#.##");
        float percentage = customer.getCreditsCompleted() / customer.getCreditsEarned() * 100;
        float format = Float.parseFloat(df.format(percentage));
        customer.setConfidence(format);
        Customer saveCustomer = customerRepository.save(customer);

        return saveCustomer != null;

    }

}
