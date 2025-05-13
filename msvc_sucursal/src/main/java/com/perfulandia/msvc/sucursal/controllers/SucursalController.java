package com.perfulandia.msvc.sucursal.controllers;

import com.perfulandia.msvc.sucursal.models.Sucursal;
import com.perfulandia.msvc.sucursal.services.SucursalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sucursales")
@Validated
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<Sucursal>> findAll(){
        return ResponseEntity
                .status(200)
                .body(this.sucursalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> findById(@PathVariable Long id){
        return ResponseEntity
                .status(200)
                .body(this.sucursalService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Sucursal> create (@Valid @RequestBody Sucursal sucursal){
        return ResponseEntity
                .status(201)
                .body(this.sucursalService.save(sucursal));
    }
}
