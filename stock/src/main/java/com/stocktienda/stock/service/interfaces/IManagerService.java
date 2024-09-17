package com.stocktienda.stock.service.interfaces;

import com.stocktienda.stock.ModelsAuxiliary.ProductsData;
import com.stocktienda.stock.dtos.dtoAuxPrice;
import com.stocktienda.stock.dtos.dtoAuxProducts;
import com.stocktienda.stock.dtos.dtoNewAddProducts;
import java.util.List;

public interface IManagerService {

    public List<ProductsData> listAlllManager();

    public boolean existsByIdProducts(Long idProducts);

    public ProductsData getOneManager(Long id);

    public boolean increase(Long idProducts, dtoAuxProducts dtoproducts);

    public Long saveProduct(dtoNewAddProducts newProduct);
    
    public boolean updatePrice(Long idProducts, dtoAuxPrice dtoprice);
    
    public boolean removedLogical(Long idProducts);

}
