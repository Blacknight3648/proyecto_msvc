package com.msvc.carrito.controller;

import com.msvc.carrito.model.entity.Carrito;
import com.msvc.carrito.services.CarritoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public ResponseEntity<List<com.msvc.carrito.dtos.CarritoDTO>> findAll() {
        return ResponseEntity
                .status(200)
                .body(this.carritoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(this.carritoService.findById(id));
    }
    @PostMapping
    public ResponseEntity<Carrito> save(@RequestBody @Valid Carrito carrito) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.carritoService.save(carrito));
    }
    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<Carrito>> findByIdCliente(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(this.carritoService.findByClienteId(id));
    }
    @GetMapping("/vendedor/{id}")
    public ResponseEntity<List<Carrito>> findByIdMedico(@PathVariable Long id) {
        return ResponseEntity.status(200).body(this.carritoService.findByVendedorId(id));
    }
}
