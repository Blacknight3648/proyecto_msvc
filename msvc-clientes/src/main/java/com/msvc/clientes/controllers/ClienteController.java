package com.msvc.clientes.controllers;

import com.msvc.clientes.DTO.ClienteDTO;
import com.msvc.clientes.models.Cliente;
import com.msvc.clientes.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@Validated
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> clientes = this.clienteService.findAll();
        return ResponseEntity.status(200).body(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        Cliente cliente = this.clienteService.findById(id);
        return ResponseEntity.status(200).body(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@Valid @RequestBody Cliente cliente) {
        Cliente nuevoCliente = this.clienteService.save(cliente);
        return ResponseEntity.status(201).body(nuevoCliente);
    }

    @PutMapping("/{id}/suspender")
    public ResponseEntity<Cliente> suspend(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente clienteSuspendido = this.clienteService.suspend(id, clienteDTO);
        return ResponseEntity.status(200).body(clienteSuspendido);
    }

}



