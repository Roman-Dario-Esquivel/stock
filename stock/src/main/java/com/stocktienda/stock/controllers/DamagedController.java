package com.stocktienda.stock.controllers;

import com.stocktienda.stock.dtos.dtoDamagedProducts;
import com.stocktienda.stock.service.interfaces.IDamagedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/damaged")
@Tag(name = "Producto Dañado", description = "Operaciones relacionadas con la creacion de producto dañado")
public class DamagedController {
    
    private final IDamagedService damagedService;
     
    @Operation(summary = "Metodo de carga de dañado ", description = "Metodo de creacion de producto dañado confirmacion con true")
    @PostMapping("/create")
    public ResponseEntity<?> createDamaged(@RequestBody dtoDamagedProducts dtodamaged) {
        return new ResponseEntity<>(this.damagedService.addDamaged( dtodamaged), HttpStatus.CREATED);
    }
     
}
