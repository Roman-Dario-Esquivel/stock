package com.stocktienda.stock.service.implementations;

import com.stocktienda.stock.ModelsAuxiliary.ProductsData;
import com.stocktienda.stock.ModelsAuxiliary.Sales;
import com.stocktienda.stock.dtos.dtoAuxCard;
import com.stocktienda.stock.dtos.dtoAuxPrice;
import com.stocktienda.stock.dtos.dtoAuxProducts;
import com.stocktienda.stock.dtos.dtoNewAddProducts;
import com.stocktienda.stock.exception.CustomException;
import com.stocktienda.stock.models.Damaged;
import com.stocktienda.stock.models.Products;
import com.stocktienda.stock.repositorys.IProductsRepository;
import com.stocktienda.stock.service.interfaces.ISalesService;
import java.util.List;
import org.springframework.stereotype.Service;
import com.stocktienda.stock.service.interfaces.IManagerService;
import com.stocktienda.stock.service.interfaces.IProductsService;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductsService implements ISalesService, IManagerService, IProductsService {

    private final IProductsRepository productsRepository;

    public ProductsService(IProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public List<ProductsData> listAlllManager() {
        List<Products> products = this.productsRepository.findAllWithoutDeleted();
        List<ProductsData> productsData = new ArrayList<>();

        for (Products product : products) {
            ProductsData auxdata = new ProductsData();
            auxdata.setIdProduct(product.getIdProduct());
            auxdata.setDescription(product.getDescription());
            auxdata.setAvailable(product.getAvailable());
            auxdata.setStock(product.getStock());
            auxdata.setLow(product.getLow());
            auxdata.setReserve(product.getReserve());
            auxdata.setSold(product.getSold());
            auxdata.setPrice(product.getPrice());
            auxdata.setCard(product.getCard());
            auxdata.setDamaged(product.getDamagedlist()
                    .stream()
                    .map(Damaged::toString)
                    .collect(Collectors.joining(".\n")));
            productsData.add(auxdata);
        }
        return productsData;
    }

    @Override
    public List<Sales> listAlllSales() {
        return this.productsRepository.viewProrductsSales();
    }

    @Override
    public ProductsData getOneManager(Long id) {
        Optional<Products> optionalProduct = productsRepository.findByIdProduct(id);
        Products product = optionalProduct
                .orElseThrow(() -> new RuntimeException("El Product con el codigo indicado no existe"));

        ProductsData auxdata = new ProductsData();
        auxdata.setIdProduct(product.getIdProduct());
        auxdata.setDescription(product.getDescription());
        auxdata.setAvailable(product.getAvailable());
        auxdata.setStock(product.getStock());
        auxdata.setLow(product.getLow());
        auxdata.setPrice(product.getPrice());
        auxdata.setCard(product.getCard());
        auxdata.setSold(product.getSold());
        auxdata.setReserve(product.getReserve());
        auxdata.setDamaged(product.getDamagedlist()
                .stream()
                .map(Damaged::toString)
                .collect(Collectors.joining("\n")));
        return auxdata;
    }

    @Override
    public Products getOneProducts(Long id) {
        return productsRepository.findByIdProduct(id)
                .orElseThrow(() -> new RuntimeException("No existe producto"));
    }

    @Override
    public Sales getOneSales(Long id) {
        return productsRepository.findByIdSales(id)
                .orElseThrow(() -> new RuntimeException("No existe producto"));
    }

    @Override
    public Long saveProduct(dtoNewAddProducts newProduct) {
        Products product = Products.builder()
                .description(newProduct.getDescription())
                .stock(newProduct.getQuantity())
                .available(newProduct.getQuantity())
                .low(0)
                .sold(0)
                .deleted(false)
                .reserve(0)
                .price(newProduct.getPrice())
                .card(0)
                .build();
        Products saveProduct = productsRepository.save(product);
        return saveProduct.getIdProduct();
    }

    @Override
    public boolean existsByIdProducts(Long idProducts) {
        return productsRepository.existsById(idProducts);
    }

    @Override
    public boolean sales(Long idProducts, dtoAuxProducts dtoproducts) {

        Products product = this.getOneProducts(idProducts);

        if (product.getAvailable() < dtoproducts.getQuantity()) {

            throw new CustomException(" La cantidad a vender excede el stock disponible");

        }

        product.setAvailable(product.getAvailable() - dtoproducts.getQuantity());
        product.setSold(product.getSold() + dtoproducts.getQuantity());
        Products saveProduct = productsRepository.save(product);

        return saveProduct != null;

    }

    @Override
    public boolean increase(Long idProducts, dtoAuxProducts dtoproducts) {
        Products product = this.getOneProducts(idProducts);
        product.setAvailable(product.getAvailable() + dtoproducts.getQuantity());
        product.setStock(product.getStock() + dtoproducts.getQuantity());
        Products saveProduct = productsRepository.save(product);
        return saveProduct != null;
    }

    @Override
    public boolean returnProduct(Long idProducts, dtoAuxProducts dtoproducts) {

        Products product = this.getOneProducts(idProducts);

        if (product.getSold() < dtoproducts.getQuantity()) {

            throw new CustomException(" La cantidad a devolver supera la cantidad de unidades vendidas");

        }

        product.setAvailable(product.getAvailable() + dtoproducts.getQuantity());
        product.setSold(product.getSold() - dtoproducts.getQuantity());
        Products saveProduct = productsRepository.save(product);
        return saveProduct != null;

    }

    @Override
    public boolean updatePrice(Long idProducts, dtoAuxPrice dtoprice) {
        Products product = this.getOneProducts(idProducts);
        product.setPrice(dtoprice.getPrice());
        Products saveProduct = productsRepository.save(product);
        return saveProduct != null;
    }

    @Override
    public boolean updateCard(Long idProducts, dtoAuxCard dtoprice) {
        Products product = this.getOneProducts(idProducts);
        product.setCard(dtoprice.getCard());
        Products saveProduct = productsRepository.save(product);
        return saveProduct != null;

    }

    @Override
    public boolean removedLogical(Long idProducts) {
        Products product = this.getOneProducts(idProducts);
        product.setDeleted(true);
        Products saveProduct = productsRepository.save(product);
        return saveProduct != null;
    }

    @Override
    public boolean createReserva(Long idProducts, long quantity) {
        Products product = this.getOneProducts(idProducts);
        if (product.getAvailable() < quantity) {

            throw new CustomException(" La cantidad a vender excede el stock disponible");

        }
        product.setReserve(product.getReserve() + quantity);
        product.setAvailable(product.getAvailable() - quantity);
        Products saveProduct = productsRepository.save(product);
        return saveProduct != null;
    }

    @Override
    public boolean salesReserva(Long idProducts, long quantity) {
        Products product = this.getOneProducts(idProducts);
        product.setReserve(product.getReserve() - quantity);
        product.setSold(product.getSold() + quantity);
        Products saveProduct = productsRepository.save(product);
        return saveProduct != null;
    }

    @Override
    public boolean cancelReserva(Long idProducts, long quantity) {
        Products product = this.getOneProducts(idProducts);
        product.setReserve(product.getReserve() - quantity);
        product.setAvailable(product.getAvailable() + quantity);
        Products saveProduct = productsRepository.save(product);
        return saveProduct != null;
    }

}
