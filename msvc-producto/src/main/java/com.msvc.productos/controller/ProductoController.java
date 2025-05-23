package com.msvc.productos.controller;

import com.msvc.productos.dtos.ProductoDTO;
import jakarta.validation.Valid;
import com.msvc.productos.model.entity.ProductoModel;
import com.msvc.productos.services.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
@Validated
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoModel>> findAll() {
    return ResponseEntity.status(HttpStatus.OK).body(productoService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductoModel> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.findById(id));
    }
    @PostMapping
    public ResponseEntity<ProductoModel> save(@Valid @RequestBody ProductoModel producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto));
    }
}
