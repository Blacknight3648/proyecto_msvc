package com.msvc_clientes.controllers;

import com.msvc_clientes.models.Cliente;
import com.msvc_clientes.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cliente")
@Validated
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>>findAll(){
        List<Cliente> clientes = this.clienteService.findAll();
        return ResponseEntity.status(200).body(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id){

        Cliente cliente = this.clienteService.findById(id);
        return ResponseEntity.status(200).body(cliente);

    }




}
