package com.msvc.productos.controller;

import com.msvc.productos.dtos.ProductoDTO;
import jakarta.validation.Valid;
import com.msvc.productos.model.entity.ProductoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.msvc.productos.services.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
@Validated
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoModel>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.productoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoModel> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(this.productoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductoModel> save(@Valid @RequestBody ProductoModel producto) {
        ProductoModel saved = this.productoService.save(producto);
        return ResponseEntity
                .status(201)
                .body(saved);
    }
    @GetMapping("/clientes/{id}")
    public ResponseEntity<List<ProductoModel>> findByIdProducto(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(this.productoService.findByClienteId(id));
    }

    @GetMapping("/comprobante/{id}")
    public ResponseEntity<List<ProductoModel>> findByIdComprobante(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(this.productoService.findByComprobanteId(id));
    }
}
