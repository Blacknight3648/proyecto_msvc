package com.msvc.carrito.controller;

import com.msvc.carrito.assembler.CarritoModelAssembler;
import com.msvc.carrito.dtos.ErrorDTO;
import com.msvc.carrito.model.entity.Carrito;
import com.msvc.carrito.services.CarritoService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/carritos")
public class CarritoControllerV2 {

    @Autowired
    private CarritoService carritoService;

    @GetMapping("/mostrar_carritos")
    @Operation(
            summary = "Devuelve todos los carritos, ",
            description = "Este metodo debe retornar una lista de carritos, en caso"+
                    " de que no se encuentre nada retorna una lista vacía"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retornaron todos los carritos", content = @Content(mediaType = MediaTypes.HAL_JSON_VALUE,schema = @Schema(implementation = Carrito.class)))
    })
    public ResponseEntity<List<com.msvc.carrito.dtos.CarritoDTO>> findAll() {
        return ResponseEntity
                .status(200)
                .body(this.carritoService.findAll());
    }

    @GetMapping("id/{id}")
    @Operation(
            summary = "Devuelve un carrito con su id",
            description = "Este metodo debe retornar un carrito cuando es consultado mediante su id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retorna el carrito encontrado"),
            @ApiResponse(
                    responseCode = "404",
                    description = "El carrito con el id solicitado no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    public ResponseEntity<Carrito> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(this.carritoService.findById(id));
    }
    @PostMapping("/crearComprobante")
    @Operation(
            summary = "Endpoint que permita guardar un carrito",
            description = "Este endpoint debe recibir un body con el formato de carrito y permitira realizar la creacion de un comprobante"
    )
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Carrito creado exitosamente")})
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Este debe ser Json con los datos de carrito",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Carrito.class)
            )
    )
    public ResponseEntity<Carrito> save(@RequestBody @Valid Carrito carrito) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.carritoService.save(carrito));
    }
    @GetMapping("/cliente/{id}")
    @Operation(
            summary = "Devuelve los carritos segun el ID del cliente",
            description = "Este metodo debe retornar el historial de un cliente mediante su id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retorna el historial de carritos del cliente"),
            @ApiResponse(
                    responseCode = "404",
                    description = "El carrito con la id de cliente solicitado no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el id unico de un cliente", required = true)
    })
    public ResponseEntity<List<Carrito>> findByIdCliente(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(this.carritoService.findByClienteId(id));
    }
    @GetMapping("/vendedor/{id}")
    @Operation(
            summary = "Devuelve los carritos segun el ID del vendedor",
            description = "Este metodo debe retornar el historial de un vendedor mediante su id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retorna el historial de carritos del vendedor"),
            @ApiResponse(
                    responseCode = "404",
                    description = "El carrito con la id de vendedor solicitado no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    public ResponseEntity<List<Carrito>> findByIdVendedor(@PathVariable Long id) {
        return ResponseEntity.status(200).body(this.carritoService.findByVendedorId(id));
    }

    public ResponseEntity<CollectionModel<EntityModel<Carrito>>> findAllModels() {
        List<EntityModel<Carrito>> entityModels = this.carritoService.findAllModels()
                .stream()
                .map(CarritoModelAssembler::toModel)
                .toList();
        CollectionModel<EntityModel<Carrito>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(CarritoControllerV2.class).findAllModels()).withSelfRel()
        );
        return ResponseEntity.status(200).body(collectionModel);
    }
}
