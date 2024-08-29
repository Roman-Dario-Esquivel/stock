package com.stocktienda.stock.service.interfaces;

import com.stocktienda.stock.ModelsAuxiliary.ProductsData;
import com.stocktienda.stock.dtos.dtoAuxProducts;
import com.stocktienda.stock.dtos.dtoNewAddProducts;
import com.stocktienda.stock.models.Products;
import java.util.List;


public interface IManagerService {
    
    public List<ProductsData> listAlllManager();
    public boolean existsByIdProducts(Long idProducts);
    public Products getOneManager(Long id);
    public Products increase(Long idProducts, dtoAuxProducts dtoproducts);
    public Products saveProduct(dtoNewAddProducts newProduct);
    
    
}
