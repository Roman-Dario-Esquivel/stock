package com.stocktienda.stock.controllers;

import com.stocktienda.stock.ModelsAuxiliary.ProductsData;
import com.stocktienda.stock.dtos.dtoAuxProducts;
import com.stocktienda.stock.dtos.dtoNewAddProducts;
import com.stocktienda.stock.models.Products;
import com.stocktienda.stock.service.interfaces.IManagerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/manager")
public class ManagerController {

    @Autowired
    private final IManagerService managerService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductsData>> listProducts() {
        List<ProductsData> list = managerService.listAlllManager();
        if (!list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Products> getByIdManager(@PathVariable("id") long id) {
        Products product = managerService.getOneManager(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createManager(@RequestBody dtoNewAddProducts dtoproduct) {
        return new ResponseEntity<>(this.managerService.saveProduct(dtoproduct), HttpStatus.CREATED);
    }

    @PutMapping("/updatestock/{id}")
    public ResponseEntity<?> updateStock(@PathVariable("id") long id, @RequestBody dtoAuxProducts dtoproducts) {
        Products products = this.managerService.increase(id, dtoproducts);
        if (products != null) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
