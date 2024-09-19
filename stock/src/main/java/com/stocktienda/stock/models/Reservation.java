package com.stocktienda.stock.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //precio
    private double price;

    //saldo
    private double balance;

    //deposito
    private double deposit;

    private boolean active;
    
    private long quantity;

    @ManyToOne(targetEntity = Customer.class, cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Customer customer;

    @ManyToOne(targetEntity = Products.class, cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Products product;

}
