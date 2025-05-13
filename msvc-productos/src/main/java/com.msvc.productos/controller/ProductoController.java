package com.msvc.productos.controller;

import jakarta.validation.Valid;
import com.msvc.productos.model.entity.ProductoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.msvc.productos.services.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/paciente")
@Validated
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoModel>> findAll(){
        List<ProductoModel> producto = this.productoService.findAll();
        return ResponseEntity
                .status(200)
                .body(producto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductoModel> save (@PathVariable Long id) {
        ProductoModel producto = this.productoService.findById(id);
        return ResponseEntity
                .status(200)
                .body(producto);
    }
    @PostMapping
    public ResponseEntity<ProductoModel> save(@Valid @RequestBody ProductoModel producto) {
        ProductoModel saved = this.productoService.save(producto);
        return ResponseEntity
                .status(201)
                .body(saved);
    }
}
