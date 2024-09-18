package com.stocktienda.stock.dtos;

import jakarta.validation.constraints.NotBlank;
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
public class dtoCustomer {

    @NotNull
    private Long dni;

    @NotBlank(message = "ingrese un nombre ")
    private String name;

    @NotBlank(message = "ingrese un numero de telefono")
    private String numberMobile;

}
