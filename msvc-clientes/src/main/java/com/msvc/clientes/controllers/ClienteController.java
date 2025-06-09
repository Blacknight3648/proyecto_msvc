package com.msvc.clientes.controllers;

import com.msvc.clientes.DTO.ClienteDTO;
import com.msvc.clientes.models.Cliente;
import com.msvc.clientes.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@Validated
@Tag(name= "Clientes", description = "Esta seccion pertenece a clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @GetMapping("/mostrar_clientes")
    @Operation(
            summary = "Devuelve todos los medicos,",
            description = "Este medtodo debe retornar una lista de clientes, en caso"+
                    "de que no se encuentre nada retorna una lista vacia"
    )
    public ResponseEntity<List<Cliente>> findAll() {

        List<Cliente> clientes = this.clienteService.findAll();
        return ResponseEntity.status(200).body(clientes);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        Cliente cliente = this.clienteService.findById(id);
        return ResponseEntity.status(200).body(cliente);
    }

    @GetMapping("/run/{runCliente}")
    public ResponseEntity<ClienteDTO> findByRun(String runCliente){
        ClienteDTO clienteDTO = this.clienteService.findByRunCliente(runCliente);
        return ResponseEntity.status(200).body(clienteDTO);

    }

    @PostMapping("/crearCliente")
    public ResponseEntity<Cliente> create(@Valid @RequestBody Cliente cliente) {
        Cliente nuevoCliente = this.clienteService.save(cliente);
        return ResponseEntity.status(201).body(nuevoCliente);
    }

    @PutMapping("/suspender/{id}")
    public ResponseEntity<Cliente> suspend(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente clienteSuspendido = clienteService.suspend(id, clienteDTO);
        return ResponseEntity.status(202).body(clienteSuspendido);
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO){
        Cliente clienteModificado = clienteService.update(id, clienteDTO);
        return ResponseEntity.status(202).body(clienteModificado);

    }

}



