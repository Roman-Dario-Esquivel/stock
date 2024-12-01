package com.stocktienda.stock.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
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
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @NotBlank
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
    
    //precio en tarjeta
    private double card;

    // eliminado
    private boolean deleted;

    @OneToMany(targetEntity = Reservation.class, fetch = FetchType.LAZY, mappedBy = "product")
    @JsonManagedReference
    @Builder.Default
    private List<Reservation> reservas = new ArrayList<>();

    @OneToMany(targetEntity = Damaged.class, fetch = FetchType.LAZY, mappedBy = "product")
    @JsonManagedReference
    @Builder.Default
    private List<Damaged> damagedlist = new ArrayList<>();

}
