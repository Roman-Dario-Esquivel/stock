package com.stocktienda.stock.service.interfaces;

import com.stocktienda.stock.ModelsAuxiliary.CustomerData;
import com.stocktienda.stock.dtos.dtoCustomer;

public interface ICustomerService {

    public CustomerData getOneCustomerData(Long dni);

    public String verifyExistence(Long dni);

    public boolean saveCustomer(dtoCustomer customer);

    public boolean editCustomer(dtoCustomer customer);

    public boolean addCreditsCompleted(Long dni);

    public boolean addCreditsEarned(Long dni);

    public boolean calculateConfidence(Long dni);

}
