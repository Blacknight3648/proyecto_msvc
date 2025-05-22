package com.smedina_msvcproveedor.controller;

import com.smedina_msvcproveedor.model.Proveedor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.ProveedorService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proveedores")
@Validated
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<Proveedor>> findAll(){
        List<Proveedor> clientes = this.proveedorService.findAll();
        return ResponseEntity.status(200).body(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> findById(@PathVariable Long id){

        Proveedor proveedor = this.proveedorService.findById(id);
        return ResponseEntity.status(200).body(proveedor);

    }

    @PostMapping
    public ResponseEntity<Proveedor> create(@Valid @RequestBody Proveedor proveedor){

        return ResponseEntity
                .status(200)
                .body(this.proveedorService.save(proveedor));

    }

}
