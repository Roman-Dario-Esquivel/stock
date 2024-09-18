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
public class ReservationData {

    private Long id;

    //precio
    private double price;

    //saldo
    private double balance;

    //deposito
    private double deposit;

    private String articulo;
    
}
