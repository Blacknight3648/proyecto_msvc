package com.msvc.controller.com.msvc.productos.controller.com.msvc.productos.controller.com.msvc.productos.controller.com.msvc.productos.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.msvc.model.entity.ProductoModel;
import com.msvc.services.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/productos")
@Validated
@Tag(name= "Productos", description = "Esta seccion contiene los CRUD de producto")
public class ProductoController {

    private final ProductoService productoService;

    // Inyección por constructor
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    @Operation(
            summary = "Find all Productos",
            description = "Encuentra todos los productos"
    )
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
