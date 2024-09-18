package com.stocktienda.stock.ModelsAuxiliary;

import java.util.ArrayList;
import java.util.List;
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
public class CustomerData {

    private Long dni;

    private String name;

    //numero de telefono
    private String numberMobile;


    @Builder.Default
    private List<ReservationData> reservations = new ArrayList<>();
    
     
}
