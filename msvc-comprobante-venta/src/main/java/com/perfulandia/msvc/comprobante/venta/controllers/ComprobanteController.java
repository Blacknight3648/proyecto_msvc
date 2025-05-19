package com.perfulandia.msvc.comprobante.venta.controllers;

import com.perfulandia.msvc.comprobante.venta.dtos.ComprobanteDTO;
import com.perfulandia.msvc.comprobante.venta.models.entities.Comprobante;
import com.perfulandia.msvc.comprobante.venta.services.ComprobanteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comprobantes")
@Validated
public class ComprobanteController {

    @Autowired
    private ComprobanteService comprobanteService;

    @GetMapping
    public ResponseEntity<List<ComprobanteDTO>> findAll(){
        return ResponseEntity.status(200).body(this.comprobanteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comprobante> findById(@PathVariable Long id){
        return ResponseEntity.status(200).body(this.comprobanteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Comprobante> save(@RequestBody @Valid Comprobante comprobante){
        return ResponseEntity.status(201).body(this.comprobanteService.save(comprobante));
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<Comprobante>> findByIdCliente(@PathVariable Long id){
        return ResponseEntity.status(200).body(this.comprobanteService.findByClienteId(id));
    }

    @GetMapping("/vendedor/{id}")
    public ResponseEntity<List<Comprobante>> findByIdVendedor(@PathVariable Long id){
        return ResponseEntity.status(200).body(this.comprobanteService.findByVendedorId(id));
    }

    @GetMapping("/sucursal/{id}")
    public ResponseEntity<List<Comprobante>> findByIdSucursal(@PathVariable Long id){
        return ResponseEntity.status(200).body(this.comprobanteService.findBySucursalId(id));
    }
}
