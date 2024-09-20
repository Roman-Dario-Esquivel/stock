package com.stocktienda.stock.service.interfaces;

import com.stocktienda.stock.ModelsAuxiliary.ReservationDataList;
import com.stocktienda.stock.dtos.dtoReservation;
import com.stocktienda.stock.dtos.dtoReservationCustomer;
import com.stocktienda.stock.models.Reservation;
import java.util.List;

public interface IReservationService {
    
    public Reservation getOneReservation(Long id);
    public List<ReservationDataList> listAllReservationActive();
    public boolean saveCustomerReservation(dtoReservationCustomer newReservation);
    public boolean saveReserva(dtoReservation newReservation);
    public double increaseDeposit(Long id, double deposit);
    public boolean salesReserva(Long id);
    public boolean cancelReserva(Long id);
}
