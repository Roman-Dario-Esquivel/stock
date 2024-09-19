package com.stocktienda.stock.controllers;

import com.stocktienda.stock.ModelsAuxiliary.ReservationCustomer;
import com.stocktienda.stock.dtos.dtoCustomer;
import com.stocktienda.stock.service.interfaces.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api/v1/customer")
@Tag(name = "clientes", description = "Operaciones relacionadas con el cliente")
public class CustomerController {

    @Autowired
    private final ICustomerService customerService;

    @GetMapping("/listCustomer/{id}")
    @Operation(summary = "El metodo devuelve un lista de reservas de cliente", description = "El metodo devuelve una lista de las reservas de un cliente")
    public ResponseEntity<List<ReservationCustomer>> getListDni(@PathVariable("id") long id) {
        List<ReservationCustomer> list = customerService.listDni(id);
        if (!list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    @Operation(summary = "Metodo de creacion de cliente", description = "Metodo de creacion de cliente nuevo confirmacion con true")
    public ResponseEntity<?> createCusomer(@RequestBody dtoCustomer dtocustomer) {
        return new ResponseEntity<>(this.customerService.saveCustomer(dtocustomer), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Metodo de actualizacion de datos", description = "Metodo de actualizacion de datos del cliente, confirmacion con true")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") long id, @RequestBody dtoCustomer dtocustomer) {
        boolean customer = this.customerService.editCustomer(dtocustomer);
        if (customer) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/Exists/{id}")
    @Operation(summary = "El metodo devuelve si existe", description = "El metodo devuelve  nombre si existe o false")
    public ResponseEntity<?> getExistsDni(@PathVariable("id") long id) {
        String name = customerService.verifyExistence(id);
        return new ResponseEntity<>(name, HttpStatus.OK);
    }

}
