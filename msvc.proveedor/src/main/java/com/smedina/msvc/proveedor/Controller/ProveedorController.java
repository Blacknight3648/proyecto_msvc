package com.smedina_msvcproveedor.controller;

import com.smedina_msvcproveedor.model.Proveedor;
import com.smedina_msvcproveedor.service.ProveedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<Proveedor>> findAll(){
        return ResponseEntity.ok(proveedorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> findById(@PathVariable Long id){
        return ResponseEntity.ok(proveedorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Proveedor> create(@Valid @RequestBody Proveedor proveedor){
        return ResponseEntity.ok(proveedorService.save(proveedor));
    }
}