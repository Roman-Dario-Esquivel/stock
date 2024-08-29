package com.stocktienda.stock.service.implementations;

import com.stocktienda.stock.dtos.dtoDamagedProducts;
import com.stocktienda.stock.exception.CustomException;
import com.stocktienda.stock.models.Damaged;
import com.stocktienda.stock.models.Products;
import com.stocktienda.stock.repositorys.IDamagedRepository;
import com.stocktienda.stock.repositorys.IProductsRepository;
import com.stocktienda.stock.service.interfaces.IDamagedService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DamagedService implements IDamagedService {

    private final IDamagedRepository damagedRepository;
    private final IProductsRepository productsRepository;

    @Autowired
    public DamagedService(IDamagedRepository damagedRepository, IProductsRepository productsRepository) {
        this.damagedRepository = damagedRepository;
        this.productsRepository = productsRepository;
    }

    @Override
    @Transactional
    public Damaged addDamaged( dtoDamagedProducts dtoproducts) {
        Products product = productsRepository.findByIdProduct(dtoproducts.getId())
                .orElseThrow(() -> new RuntimeException("producto no existe"));
    
        if((product.getAvailable()+product.getSold()== product.getStock())&& (product.getSold()>=dtoproducts.getQuantity())){
        Damaged damaged = Damaged.builder()
                .quantity(dtoproducts.getQuantity())
                .product(product)
                .build();

        product.setAvailable(product.getAvailable() - dtoproducts.getQuantity());
        product.setLow(product.getLow() + dtoproducts.getQuantity());
        product.getDamagedlist().add(damaged);
        productsRepository.save(product);
        return damagedRepository.save(damaged);
        
        
        }else{
            throw new CustomException("Ocurrió un error en el servicio no se puede devolver no coincide con venta");
       
        }
        
    }
}
