package com.stocktienda.stock.ModelsAuxiliary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDataList {
    
    
    private Long id;

    //precio
    private double price;

    //saldo
    private double balance;

    //deposito
    private double deposit;
    
    //cantidad
    private long quantity;
    
    //cliente
    private String name;
    
    private String numberMobile;
    
    private Long dni;
    
    // producto
    private Long codigo;

    private String articulo;
    
}
