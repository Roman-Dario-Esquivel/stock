package com.stocktienda.stock.service.interfaces;

import com.stocktienda.stock.dtos.dtoDamagedProducts;
import com.stocktienda.stock.models.Damaged;

public interface IDamagedService {

    public Damaged addDamaged(dtoDamagedProducts dtoproducts);

}
