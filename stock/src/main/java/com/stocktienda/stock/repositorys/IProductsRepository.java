package com.stocktienda.stock.repositorys;

import com.stocktienda.stock.ModelsAuxiliary.Sales;
import com.stocktienda.stock.models.Products;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductsRepository extends JpaRepository<Products, Long>{
    
    public Optional<Products> findByIdProduct(Long id);

    @Query("SELECT  new com.stocktienda.stock.ModelsAuxiliary.Sales(p.idProduct, p.description, p.available, p.sold, p.reserve, p.price) FROM Products p WHERE p.idProduct =:id")
    public Optional<Sales> findByIdSales(@Param("id") Long id);
    
    @Query("SELECT p FROM Products p WHERE p.deleted = false")
    public List<Products> findAllWithoutDeleted();
    
    public Optional<Products> findByDescription(String name);

    public boolean existsByDescription(String name);
    
    @Query("SELECT new com.stocktienda.stock.ModelsAuxiliary.Sales(p.idProduct, p.description, p.available, p.sold, p.reserve, p.price) FROM Products p WHERE p.deleted = false")
    public List<Sales> viewProrductsSales();
    
}
