package com.msvc.clientes.controllers;

import com.msvc.clientes.DTO.ClienteDTO;
import com.msvc.clientes.DTO.ErrorDTO;
import com.msvc.clientes.models.Cliente;
import com.msvc.clientes.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retornan todos los clientes")
    })
    public ResponseEntity<List<Cliente>> findAll() {

        List<Cliente> clientes = this.clienteService.findAll();
        return ResponseEntity.status(200).body(clientes);
    }

    @GetMapping("/id/{id}")
    @Operation(
            summary = "Devuelve un cliente respecto a su id",
            description = "Este metodo debe retornar un Cliente cuando es consultado"+
                    "mediante su Id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se  retorna el cliente encontrado"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error - cliente con Id no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el id unico de un cliente",required = true)
    })
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
    @Operation(
            summary = "Endpoint que me permite guardar un cliente",
            description = "Este endpoint debo mandar un body con el formato de Cliente.class "+
                    "y me permitira realizar la creacion de un cliente"
    )
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Cliente creado correctamente")})
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Este debe ser Json con los datos de cliente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Cliente.class )

            )
    )
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

    @DeleteMapping("/eliminar_cliente/{id}")
    public ResponseEntity<Cliente> deleteById(@PathVariable Long id){

        Cliente cliente = clienteService.deleteById(id);
        return ResponseEntity.status(202).body(cliente);

    }

}



