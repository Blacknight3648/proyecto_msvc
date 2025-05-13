package com.msvc.vendedor.controllers;

import com.msvc.vendedor.models.Vendedor;
import com.msvc.vendedor.services.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vendedor")
@Validated
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @GetMapping
    public ResponseEntity<List<Vendedor>>findAll(){
        List<Vendedor> vendedores = this.vendedorService.findAll();
        return ResponseEntity.status(200).body(vendedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> findById(@PathVariable Long id){

        Vendedor vendedor = this.vendedorService.findById(id);
        return ResponseEntity.status(200).body(vendedor);

    }



}
