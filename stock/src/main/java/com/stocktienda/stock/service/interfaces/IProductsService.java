package com.stocktienda.stock.service.interfaces;

import com.stocktienda.stock.ModelsAuxiliary.ProductsData;
import com.stocktienda.stock.ModelsAuxiliary.Sales;
import com.stocktienda.stock.dtos.dtoAuxPrice;
import com.stocktienda.stock.dtos.dtoAuxProducts;
import com.stocktienda.stock.dtos.dtoNewAddProducts;
import com.stocktienda.stock.models.Products;
import com.stocktienda.stock.models.Reservation;
import java.util.List;

public interface IProductsService {

    public List<ProductsData> listAlllManager();

    public boolean existsByIdProducts(Long idProducts);

    public ProductsData getOneManager(Long id);

    public boolean increase(Long idProducts, dtoAuxProducts dtoproducts);

    public Long saveProduct(dtoNewAddProducts newProduct);

    public boolean updatePrice(Long idProducts, dtoAuxPrice dtoprice);

    public boolean removedLogical(Long idProducts);

    public boolean cancelReserva(Long idProducts, long quantity);

    public boolean salesReserva(Long idProducts, long quantity);

    public boolean createReserva(Long idProducts, long quantity);

    public List<Sales> listAlllSales();

    public Sales getOneSales(Long id);

    public boolean sales(Long idProducts, dtoAuxProducts dtoproducts);

    public boolean returnProduct(Long idProducts, dtoAuxProducts dtoproducts);

    public Products getOneProducts(Long id);
    
}
