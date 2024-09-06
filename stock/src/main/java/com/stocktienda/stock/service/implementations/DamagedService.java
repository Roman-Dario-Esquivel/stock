package com.stocktienda.stock.service.implementations;

import com.stocktienda.stock.dtos.dtoDamagedProducts;
import com.stocktienda.stock.exception.CustomException;
import com.stocktienda.stock.models.Damaged;
import com.stocktienda.stock.models.Products;
import com.stocktienda.stock.repositorys.IDamagedRepository;
import com.stocktienda.stock.repositorys.IProductsRepository;
import com.stocktienda.stock.service.interfaces.IDamagedService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DamagedService implements IDamagedService {

    private final IDamagedRepository damagedRepository;
    private final IProductsRepository productsRepository;

    
    public DamagedService(IDamagedRepository damagedRepository, IProductsRepository productsRepository) {
        this.damagedRepository = damagedRepository;
        this.productsRepository = productsRepository;
    }

    @Override
    @Transactional
    public boolean addDamaged(dtoDamagedProducts dtodamaged) {
        Products product = productsRepository.findByIdProduct(dtodamaged.getId())
                .orElseThrow(() -> new RuntimeException("producto no existe"));

        if (product.getAvailable() >= dtodamaged.getQuantity()) {
            if (!damagedRepository.existsByDescription(dtodamaged.getReason())) {
                Damaged damaged = Damaged.builder()
                        .description(dtodamaged.getReason())
                        .product(product)
                        .build();
                product.getDamagedlist().add(damaged);
                damagedRepository.save(damaged);
            }

            product.setAvailable(product.getAvailable() - dtodamaged.getQuantity());
            product.setLow(product.getLow() + dtodamaged.getQuantity());

            Products saveDamaged = productsRepository.save(product);
            return saveDamaged != null;

        } else {
            throw new CustomException("Ocurrió un error en el servicio. no se puede devolver no hay mercaderia suficiente");

        }

    }
}
