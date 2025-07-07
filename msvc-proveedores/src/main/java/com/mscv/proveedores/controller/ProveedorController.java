package com.mscv.proveedores.controller;

import com.mscv.proveedores.Exceptions.ProveedorException;
import com.mscv.proveedores.model.Proveedor;
import com.mscv.proveedores.service.ProveedorService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proveedores")
@Validated
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    // READ: obtener todos
    @GetMapping
    public ResponseEntity<List<Proveedor>> findAll() {
        List<Proveedor> proveedores = proveedorService.findAll();
        return ResponseEntity.ok(proveedores);
    }

    // READ: obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> findById(@PathVariable Long id) {
        Proveedor proveedor = proveedorService.findById(id);
        return ResponseEntity.ok(proveedor);
    }

    // CREATE: crear nuevo proveedor
    @PostMapping
    public ResponseEntity<Proveedor> create(@Valid @RequestBody Proveedor proveedor) {
        Proveedor nuevoProveedor = proveedorService.save(proveedor);
        return ResponseEntity.status(201).body(nuevoProveedor);
    }

    // UPDATE: actualizar datos completos usando modelo
    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> update(@PathVariable Long id, @RequestBody Proveedor proveedor) throws ProveedorException {
        proveedor.setIdProveedor(id);
        Proveedor actualizado = proveedorService.update(proveedor);
        return ResponseEntity.ok(actualizado);
    }


    // DELETE: eliminar proveedor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        proveedorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}


