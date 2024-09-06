package com.stocktienda.stock.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
@Builder
public class UserDto {

    @NotBlank(message = "No puede estar vacio")
    private String username;
    
    @NotBlank(message = "No puede estar vacio")
    private String password;

}
