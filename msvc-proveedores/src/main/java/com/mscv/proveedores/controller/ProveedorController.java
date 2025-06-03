package com.mscv.proveedores.controller;

import com.mscv.proveedores.DTO.ProveedorDTO;
import com.mscv.proveedores.model.Proveedores;

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
    private com.mscv.proveedores.service.ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<Proveedores>> findAll() {
        List<Proveedores> proveedores = this.proveedorService.findAll();
        return ResponseEntity.status(200).body(proveedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedores> findById(@PathVariable Long id) {
        Proveedores proveedor = this.proveedorService.findById(id);
        return ResponseEntity.status(200).body(proveedor);
    }


    @PostMapping
    public ResponseEntity<Proveedores> create(@Valid @RequestBody Proveedores proveedor) {
        Proveedores nuevoProveedor = this.proveedorService.save(proveedor);
        return ResponseEntity.status(201).body(nuevoProveedor);
    }

    @PutMapping("/{id}/suspender")
    public ResponseEntity<Proveedores> suspend(@PathVariable Long id, @Valid @RequestBody ProveedorDTO proveedorDTO) {
        Proveedores proveedorSuspendido = this.proveedorService.suspend(id, proveedorDTO);
        return ResponseEntity.status(200).body(proveedorSuspendido);
    }

    /*@PutMapping("/{id}/actualizar")
    public ResponseEntity<Proveedores> update(@PathVariable Long id, @Valid @RequestBody ProveedorDTO proveedorDTO) {
        Proveedores proveedorActualizado = this.proveedorService.suspend(id, proveedorDTO);
        return ResponseEntity.status(200).body(proveedorActualizado);
    }*/

}


