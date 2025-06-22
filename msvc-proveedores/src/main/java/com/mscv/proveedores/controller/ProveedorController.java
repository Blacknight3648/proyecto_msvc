package com.mscv.proveedores.controller;

import com.mscv.proveedores.Exceptions.ProveedorException;
import com.mscv.proveedores.model.Proveedores;
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
    public ResponseEntity<List<Proveedores>> findAll() {
        List<Proveedores> proveedores = proveedorService.findAll();
        return ResponseEntity.ok(proveedores);
    }

    // READ: obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Proveedores> findById(@PathVariable Long id) {
        Proveedores proveedor = proveedorService.findById(id);
        return ResponseEntity.ok(proveedor);
    }

    // CREATE: crear nuevo proveedor
    @PostMapping
    public ResponseEntity<Proveedores> create(@Valid @RequestBody Proveedores proveedor) {
        Proveedores nuevoProveedor = proveedorService.save(proveedor);
        return ResponseEntity.status(201).body(nuevoProveedor);
    }

    // UPDATE: actualizar datos completos usando modelo
    @PutMapping("/{id}")
    public ResponseEntity<Proveedores> update(@PathVariable Long id, @RequestBody Proveedores proveedor) throws ProveedorException {
        proveedor.setIdProveedor(id);
        Proveedores actualizado = proveedorService.update(proveedor);
        return ResponseEntity.ok(actualizado);
    }


    // DELETE: eliminar proveedor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        proveedorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}


