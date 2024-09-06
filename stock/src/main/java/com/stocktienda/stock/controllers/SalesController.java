package com.stocktienda.stock.controllers;

import com.stocktienda.stock.ModelsAuxiliary.Sales;
import com.stocktienda.stock.dtos.dtoAuxProducts;
import com.stocktienda.stock.models.Products;
import com.stocktienda.stock.service.interfaces.ISalesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "vendedor ", description = "Operaciones relacionadas con la venta")

public class SalesController {

    @Autowired
    private final ISalesService salesService;

    
    @GetMapping("/sales")
    @Operation(summary = "Metodo que devuelve una  lista de ventas", description = "Devuelve una lista de productos para venta")
    public ResponseEntity<List<Sales>> listSales() {
        List<Sales> list = salesService.listAlllSales();
        if (!list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/detail/{id}")
    @Operation(summary = " Metodo que devuelve un producto", description = "Devuelve una lista de productos para venta")
    public ResponseEntity<Sales> getByIdSales(@PathVariable("id") long id) {
        Sales sales = salesService.getOneSales(id);
        if (sales != null) {
            return new ResponseEntity<>(sales, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/sales/{id}")
    @Operation(summary = "Metodo de compra", description = "Metodo de compra devuelve confirmacion con true")
    public ResponseEntity<?> salesProduct(@PathVariable("id") long id, @RequestBody dtoAuxProducts dtoproduct) {
        boolean product = this.salesService.sales(id, dtoproduct);
        if (product) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/return/{id}")
    @Operation(summary = "Metodo de devolucion", description = "Devuelve un producto el cliente confirma true ")
    public ResponseEntity<?> productReturn(@PathVariable("id") long id, @RequestBody dtoAuxProducts dtoproduct) {
        boolean product = this.salesService.returnProduct(id, dtoproduct);
        if (product) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
