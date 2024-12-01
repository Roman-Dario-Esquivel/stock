package com.stocktienda.stock.controllers;

import com.stocktienda.stock.ModelsAuxiliary.ReservationDataList;
import com.stocktienda.stock.dtos.dtoNewReservation;
import com.stocktienda.stock.dtos.dtoReservation;
import com.stocktienda.stock.dtos.dtoReservationCustomer;
import com.stocktienda.stock.service.interfaces.IReservationService;
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
@RequestMapping("/api/v1/reservation")
@Tag(name = "Reservacion", description = "Operaciones relacionadas con la reserva")
public class ReservationController {
    
    
    @Autowired
    private final IReservationService reservationService;
    
    
    @GetMapping("/list")
    @Operation(summary = "Metodo devuelve el listado de reserva activas", description = "Metodo devuelve el listado de reserva activas")
    public ResponseEntity<List<ReservationDataList>> listReservations() {
        List<ReservationDataList> list = reservationService.listAllReservationActive();
        if (!list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/createCustomer")
    @Operation(summary = "Metodo de creacion de reserva y alta de cliente", description = "Metodo de creacion de reserva y alta de cliente nuevo confirmacion con true")
    public ResponseEntity<?> createReservationCustomer(@RequestBody dtoReservationCustomer newreservation) {
        return new ResponseEntity<>(this.reservationService.saveCustomerReservation(newreservation), HttpStatus.CREATED);
    }
    
    @PostMapping("/create")
    @Operation(summary = "Metodo de creacion de reserva", description = "Metodo de creacion de reserva nuevo confirmacion con true")
    public ResponseEntity<?> createReservation(@RequestBody dtoReservation newreservation) {
        return new ResponseEntity<>(this.reservationService.saveReserva(newreservation), HttpStatus.CREATED);
    }
    
   
    @PutMapping("/updatedeposit/{id}")
    @Operation(summary = "Metodo de actualizacion de deposito", description = "Metodo de actualizacion de deposito confirmacion con true")
    public ResponseEntity<?> updateDeposit(@PathVariable("id") long id, @RequestBody dtoNewReservation deposit) {
        double reserva = this.reservationService.increaseDeposit(id,deposit.getDepositNew());
            return new ResponseEntity<>(reserva, HttpStatus.OK);
    }

    @DeleteMapping("/anular/{id}")
    @Operation(summary = "Metodo de anulacion de reserva", description = "Metodo de anulacion de reserva confirmacion con true")
    public ResponseEntity<?> anularReservation(@PathVariable Long id) {
        boolean annul = this.reservationService.cancelReserva(id);
        if (annul) {
            return new ResponseEntity<>(annul, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } 
    
    @PutMapping("/complete/{id}")
    @Operation(summary = "Metodo de finalizacion de zapatilla", description = "Metodo de finalizacion de productos confirmacion con true")
    public ResponseEntity<?> completeReservation(@PathVariable("id") long id) {
        boolean reservar = this.reservationService.salesReserva(id);
        if (reservar) {
            return new ResponseEntity<>(reservar, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
