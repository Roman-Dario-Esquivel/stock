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
            boolean encontrado = false;
            for (Damaged damaged : product.getDamagedlist()) {
                if (damaged.getDescription().equals(dtodamaged.getReason().toLowerCase())) {
                    encontrado = true;
                    break;
                }
            }

            // Si no se encuentra el producto, agregarlo al final
            if (!encontrado) {
                Damaged damagedsave = Damaged.builder()
                        .description(dtodamaged.getReason().toLowerCase())
                        .product(product)
                        .build();
                product.getDamagedlist().add(damagedsave);
                damagedRepository.save(damagedsave);
            }
            
            product.setAvailable(product.getAvailable() - dtodamaged.getQuantity());
            product.setLow(product.getLow() + dtodamaged.getQuantity());

            Products saveDamaged = productsRepository.save(product);
            return saveDamaged != null;

        } else {

            throw new CustomException("No se puede  informar como dañado no hay productos disponibles para dar de baja ");

        }

    }
}
