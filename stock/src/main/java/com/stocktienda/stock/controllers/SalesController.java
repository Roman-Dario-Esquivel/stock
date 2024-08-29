package com.stocktienda.stock.controllers;

import com.stocktienda.stock.ModelsAuxiliary.Sales;
import com.stocktienda.stock.dtos.dtoAuxProducts;
import com.stocktienda.stock.models.Products;
import com.stocktienda.stock.service.interfaces.ISalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sales")
public class SalesController {

    @Autowired
    private final ISalesService salesService;

    /*
    -public List<Sales> listAlllSales();
    -public Sales getOneSales(Long id);
    public boolean existsByIdProducts(Long idProducts);
    public Products sales(Long idProducts, dtoAuxProducts dtoproducts);
    public Products returnProduct(Long idProducts, dtoAuxProducts dtoproducts);
     */
    @GetMapping("/sales")
    public ResponseEntity<List<Sales>> listSales() {
        List<Sales> list = salesService.listAlllSales();
        if (!list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Sales> getByIdSales(@PathVariable("id") long id) {
        Sales sales = salesService.getOneSales(id);
        if (sales != null) {
            return new ResponseEntity<>(sales, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/sales/{id}")
    public ResponseEntity<?> salesProduct(@PathVariable("id") long id, @RequestBody dtoAuxProducts dtoproduct) {
        Products product = this.salesService.sales(id, dtoproduct);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<?> productReturn(@PathVariable("id") long id, @RequestBody dtoAuxProducts dtoproduct) {
        Products product = this.salesService.returnProduct(id, dtoproduct);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
