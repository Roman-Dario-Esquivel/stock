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
public class ProductsData {
    
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
    //lista de dañados
    private String damaged;

}
