package com.stocktienda.stock.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Customer {

    @Id
    private Long dni;

    private String name;

    //cantidad de creditos optenidos
    private int creditsEarned;

    // cantidad de creditos finalizados
    private int creditsCompleted;

    //numero de telefono
    private String numberMobile;

    // confianza
    private float confidence;

    @OneToMany(targetEntity = Reservation.class, fetch = FetchType.LAZY, mappedBy = "customer")
    @JsonManagedReference
    @Builder.Default
    private List<Reservation> reservations = new ArrayList<>();

}
