package com.msvc.controller.com.msvc.productos.controller.com.msvc.productos.controller.com.msvc.productos.controller.com.msvc.productos.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msvc.model.entity.ProductoModel;
import com.msvc.services.ProductoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Data
@Setter
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
