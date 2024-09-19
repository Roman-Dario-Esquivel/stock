package com.stocktienda.stock.service.interfaces;

import com.stocktienda.stock.ModelsAuxiliary.ReservationCustomer;
import com.stocktienda.stock.dtos.dtoCustomer;
import com.stocktienda.stock.models.Customer;
import java.util.List;

public interface ICustomerService {

    public Customer getOneCustomer(Long dni);
    
    public List<ReservationCustomer> listDni(Long dni);

    public String verifyExistence(Long dni);

    public boolean saveCustomer(dtoCustomer customer);

    public boolean editCustomer(dtoCustomer customer);

    public boolean addCreditsCompleted(Long dni);

    public boolean addCreditsEarned(Long dni);

    public boolean calculateConfidence(Long dni);
    
}
