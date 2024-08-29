package com.stocktienda.stock.controllers;

import com.stocktienda.stock.dtos.dtoDamagedProducts;
import com.stocktienda.stock.service.interfaces.IDamagedService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/damaged")
public class DamagedController {
    
    private final IDamagedService damagedService;
     
    @PostMapping("/create")
    public ResponseEntity<?> createDamaged(@RequestBody dtoDamagedProducts dtodamaged) {
        return new ResponseEntity<>(this.damagedService.addDamaged( dtodamaged), HttpStatus.CREATED);
    }
     
}
