package com.stocktienda.stock.repositorys;

import com.stocktienda.stock.ModelsAuxiliary.Venta;
import com.stocktienda.stock.models.Products;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductsRepository extends JpaRepository<Products, Long>{
    
    public Optional<Products> findByIdProduct(long id);

    public Optional<Products> findByDescription(String name);

    public boolean existsByDescription(String name);
    
    @Query("SELECT new com.stocktienda.stock.ModelsAuxiliary.Venta(p.idProduct, p.description, p.available) FROM Product p")
    public List<Venta> mostrarProrductsVenta();
    
}
