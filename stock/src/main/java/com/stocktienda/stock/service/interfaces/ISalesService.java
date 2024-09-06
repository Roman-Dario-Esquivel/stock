package com.stocktienda.stock.service.interfaces;

import com.stocktienda.stock.ModelsAuxiliary.Sales;
import com.stocktienda.stock.dtos.dtoAuxProducts;
import java.util.List;

public interface ISalesService {

    public List<Sales> listAlllSales();

    public Sales getOneSales(Long id);

    public boolean existsByIdProducts(Long idProducts);

    public boolean sales(Long idProducts, dtoAuxProducts dtoproducts);

    public boolean returnProduct(Long idProducts, dtoAuxProducts dtoproducts);

}
