package com.stocktienda.stock.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Validated
public class dtoReservation {

    @NotNull
    private Long dni;

    @NotNull
    private double deposit;

    @NotNull
    private Long code;

    private int quantity;
}
