package com.msvc.clientes.controllers;

import com.msvc.clientes.DTO.ClienteDTO;
import com.msvc.clientes.DTO.ErrorDTO;
import com.msvc.clientes.assamblers.ClienteDTOModelAssembler;
import com.msvc.clientes.assamblers.ClienteModelAssamabler;
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
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/clientes")
@Validated
@Tag(name= "Clientes HATEOAS", description = "Esta seccion contiene los CRUD a clientes")
public class ClienteControllerV2 {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteModelAssamabler clienteModelAssamabler;

    @Autowired
    private ClienteDTOModelAssembler clienteDTOModelAssembler;


    @GetMapping(value = "/mostrar_clientes" ) /* produces = MediaType.HAL_JSON_VALUE*/
    @Operation(
            summary = "Devuelve todos los clientes,",
            description = "Este metodo debe retornar una lista de clientes, en caso"+
                    "de que no se encuentre nada retorna una lista vacia"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retornan todos los clientes", content = @Content (mediaType =MediaTypes.HAL_JSON_VALUE,schema = @Schema (implementation = Cliente.class)))
    })
    public ResponseEntity<CollectionModel<EntityModel<Cliente>>> findAll() {

        List<EntityModel<Cliente>> entityModels = this.clienteService.findAll()
                .stream()
                .map(clienteModelAssamabler::toModel)
                .toList();
        CollectionModel<EntityModel<Cliente>> collectionModel = CollectionModel.of(

                entityModels,
                linkTo(methodOn(ClienteControllerV2.class).findAll()).withSelfRel()

        );
        return ResponseEntity.status(200).body(collectionModel);
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
    public ResponseEntity<EntityModel<Cliente>> findById(@PathVariable Long id) {
        EntityModel<Cliente> entityModel= this.clienteModelAssamabler.toModel(
                clienteService.findById(id)
        );
        return ResponseEntity.status(200).body(entityModel);
    }

    @GetMapping("/run/{runCliente}")
    @Operation(
            summary = "Devuelve un cliente con respecto a su rut",
            description = "Este metodo debe retornar un cliente cuando es consultado"+
                    " mediante su rut"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna el cliente encontrado"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error - Cliente con rut no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "rut", description = "Este es el rut unico de un cliente", required = true)
    })
    public ResponseEntity<EntityModel<ClienteDTO>> findByRun(String runCliente){
        EntityModel<ClienteDTO> entityModel = this.clienteDTOModelAssembler.toModel(
                clienteService.findByRunCliente(runCliente)
        );
        return ResponseEntity.status(200).body(entityModel);

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
    @Operation(
            summary = "Endpoint que permite suspender un cliente",
            description = "Este endpoint recibe el ID del cliente por la URL y un cuerpo con datos en formato ClienteDTO para suspender al cliente correspondiente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Cliente suspendido correctamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Debe enviarse un JSON con los datos requeridos del cliente a suspender",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ClienteDTO.class)
            )
    )
    public ResponseEntity<Cliente> suspend(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente clienteSuspendido = clienteService.suspend(id, clienteDTO);
        return ResponseEntity.status(202).body(clienteSuspendido);
    }

    @PutMapping("/modificar/{id}")
    @Operation(
            summary = "Endpoint que permite modificar un cliente existente",
            description = "Este endpoint permite actualizar los datos de un cliente específico. " +
                    "Se debe enviar el ID del cliente como parte de la URL y un cuerpo en formato ClienteDTO con los datos modificados."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Cliente modificado correctamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida o datos incorrectos")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Debe enviarse un JSON con los datos modificados del cliente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ClienteDTO.class)
            )
    )
    public ResponseEntity<Cliente> update(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO){
        Cliente clienteModificado = clienteService.update(id, clienteDTO);
        return ResponseEntity.status(202).body(clienteModificado);

    }

    @DeleteMapping("/eliminar_cliente/{id}")
    @Operation(
            summary = "Eliminar un cliente por ID",
            description = "Este endpoint permite eliminar un cliente específico utilizando su ID. " +
                    "Devuelve el cliente eliminado si la operación fue exitosa."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Cliente eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado"),
            @ApiResponse(responseCode = "400", description = "ID inválido")
    })
    public ResponseEntity<Cliente> deleteById(@PathVariable Long id){

        Cliente cliente = clienteService.deleteById(id);
        return ResponseEntity.status(202).body(cliente);

    }
}



