package com.stocktienda.stock.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Damaged {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDamaged;
    
    @NotBlank
    private String description;
    
    @NotNull
    private int quantity;
    
    @ManyToOne(targetEntity = Products.class, cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Products product;
    
}
