package com.stocktienda.stock.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @Id
    private Long idProduct;

    private String description;

    //disponibles
    private Long available;

    //total
    private Long stock;

    //bajas
    private long low;

    //vendidos
    private long sold;

    //reservar
    private long reserve;

    //precio
    private double price;

    //lista de dañados
    private String damaged;

}
