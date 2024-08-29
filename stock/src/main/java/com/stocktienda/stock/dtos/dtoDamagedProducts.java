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
public class dtoDamagedProducts {

    @NotNull
    private Long id;
    @NotBlank(message = "cant be empty")
    private String reason;
    @NotNull 
    private int quantity;

}
