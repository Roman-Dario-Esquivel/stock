package com.stocktienda.stock.controllers;

import com.stocktienda.stock.ModelsAuxiliary.ProductsData;
import com.stocktienda.stock.dtos.dtoAuxCard;
import com.stocktienda.stock.dtos.dtoAuxPrice;
import com.stocktienda.stock.dtos.dtoAuxProducts;
import com.stocktienda.stock.dtos.dtoNewAddProducts;
import com.stocktienda.stock.service.interfaces.IManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@Tag(name = "Administrador", description = "Operaciones relacionadas con la administracion")
public class ManagerController {

    @Autowired
    private final IManagerService managerService;

    @GetMapping("/products")
    @Operation(summary = "Metodo lista productos administrador", description = "Metodo lista productos administrador asi observa")
    public ResponseEntity<List<ProductsData>> listProducts() {
        List<ProductsData> list = managerService.listAlllManager();
        if (!list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/detail/{id}")
    @Operation(summary = "Metodo que devuelve uno administracion", description = "Metodo que devuelve uno administracion ")
    public ResponseEntity<ProductsData> getByIdManager(@PathVariable("id") long id) {
        ProductsData product = managerService.getOneManager(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    @Operation(summary = "Metodo de creacion de producto", description = "Metodo de creacion de producto nuevo confirmacion con true")
    public ResponseEntity<?> createManager(@RequestBody dtoNewAddProducts dtoproduct) {
        return new ResponseEntity<>(this.managerService.saveProduct(dtoproduct), HttpStatus.CREATED);
    }

    @PutMapping("/updatestock/{id}")
    @Operation(summary = "Metodo de actualizacion de stock", description = "Metodo de actualizacion de stock confirmacion con true")
    public ResponseEntity<?> updateStock(@PathVariable("id") long id, @RequestBody dtoAuxProducts dtoproducts) {
        boolean products = this.managerService.increase(id, dtoproducts);
        if (products) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updatesprice/{id}")
    @Operation(summary = "Metodo de actualizacion de Precio", description = "Metodo de actualizacion de precio confirmacion con true")
    public ResponseEntity<?> updatePrice(@PathVariable("id") long id, @RequestBody dtoAuxPrice dtoprice) {
        boolean products = this.managerService.updatePrice(id, dtoprice);
        if (products) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updatescard/{id}")
    @Operation(summary = "Metodo de actualizacion de Precio de tarjeta", description = "Metodo de actualizacion de precio de tarjeta confirmacion con true")
    public ResponseEntity<?> updateCard(@PathVariable("id") long id, @RequestBody dtoAuxCard dtoprice) {
        boolean products = this.managerService.updateCard(id, dtoprice);
        if (products) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Metodo de eliminacion de productos", description = " Metodo de eliminacion de productos confirmacion con true")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        boolean products = this.managerService.removedLogical(id);
        if (products) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
