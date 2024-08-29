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
public class Sales {
    
    private Long idProduct;

    
    private String description;

    //disponibles
    private Long available;
    
}
