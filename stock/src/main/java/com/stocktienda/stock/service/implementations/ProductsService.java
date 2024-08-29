package com.stocktienda.stock.service.implementations;

import com.stocktienda.stock.ModelsAuxiliary.ProductsData;
import com.stocktienda.stock.ModelsAuxiliary.Sales;
import com.stocktienda.stock.dtos.dtoAuxProducts;
import com.stocktienda.stock.dtos.dtoNewAddProducts;
import com.stocktienda.stock.exception.CustomException;
import com.stocktienda.stock.models.Damaged;
import com.stocktienda.stock.models.Products;
import com.stocktienda.stock.repositorys.IProductsRepository;
import com.stocktienda.stock.service.interfaces.ISalesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stocktienda.stock.service.interfaces.IManagerService;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class ProductsService implements ISalesService, IManagerService {

    private final IProductsRepository productsRepository;

    @Autowired
    public ProductsService(IProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public List<ProductsData> listAlllManager() {
        List<Products> products = this.productsRepository.findAll();
        List<ProductsData> productsData = new ArrayList<>();

        for (Products product : products) {
            ProductsData auxdata = new ProductsData();
            auxdata.setIdProduct(product.getIdProduct());
            auxdata.setDescription(product.getDescription());
            auxdata.setAvailable(product.getAvailable());
            auxdata.setStock(product.getStock());
            auxdata.setLow(product.getLow());
            auxdata.setSold(product.getSold());
            auxdata.setDamaged(product.getDamagedlist()
                    .stream()
                    .map(Damaged::toString)
                    .collect(Collectors.joining(".<br>" )));
            productsData.add(auxdata);

        }
        return productsData;
    }

    @Override
    public List<Sales> listAlllSales() {
        return this.productsRepository.mostrarProrductsVenta();
    }

    @Override
    public Products getOneManager(Long id) {
        return productsRepository.findByIdProduct(id)
                .orElseThrow(() -> new RuntimeException("Menu with that ID dont exist"));
    }

    @Override
    public Sales getOneSales(Long id) {
        return productsRepository.findByIdSales(id)
                .orElseThrow(() -> new RuntimeException("Menu with that ID dont exist"));
    }

    @Override
    public Products saveProduct(dtoNewAddProducts newProduct) {
        Products product = Products.builder()
                .description(newProduct.getDescription())
                .stock(newProduct.getQuantity())
                .available(newProduct.getQuantity())
                .low(0)
                .sold(0)
                .build();
        return productsRepository.save(product);
    }

    @Override
    public boolean existsByIdProducts(Long idProducts) {
        return productsRepository.existsById(idProducts);
    }

    @Override
    public Products sales(Long idProducts, dtoAuxProducts dtoproducts) {

        Products product = this.getOneManager(idProducts);

        if (product.getAvailable() < dtoproducts.getQuantity()) {

            throw new CustomException("Ocurrió un error en el servicio no se puede llevar a cabo la operacion no hay stock suficiente");

        }

        product.setAvailable(product.getAvailable() - dtoproducts.getQuantity());
        product.setSold(product.getSold() + dtoproducts.getQuantity());
        return productsRepository.save(product);

    }

    @Override
    public Products increase(Long idProducts, dtoAuxProducts dtoproducts) {
        Products product = this.getOneManager(idProducts);
        product.setAvailable(product.getAvailable() + dtoproducts.getQuantity());
        product.setStock(product.getStock() + dtoproducts.getQuantity());
        return productsRepository.save(product);
    }

    @Override
    public Products returnProduct(Long idProducts, dtoAuxProducts dtoproducts) {

        Products product = this.getOneManager(idProducts);

        if (product.getSold() < dtoproducts.getQuantity()) {

            throw new CustomException("Ocurrió un error en el servicio no se puede devolver devido que es maas de lo vendido");

        }

        product.setAvailable(product.getAvailable() + dtoproducts.getQuantity());
        product.setSold(product.getSold() - dtoproducts.getQuantity());
        return productsRepository.save(product);

    }

}
