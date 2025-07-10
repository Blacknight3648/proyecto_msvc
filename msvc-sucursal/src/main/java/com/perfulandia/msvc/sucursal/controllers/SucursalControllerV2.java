package com.perfulandia.msvc.sucursal.controllers;

import com.perfulandia.msvc.sucursal.assemblers.SucursalModelAssembler;
import com.perfulandia.msvc.sucursal.dtos.ErrorDTO;
import com.perfulandia.msvc.sucursal.models.Sucursal;
import com.perfulandia.msvc.sucursal.services.SucursalService;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/sucursales")
@Validated
@Tag(name = "Sucursales HATEOAS", description = "Esta seccion contiene los CRUD de sucursal")
public class SucursalControllerV2 {

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private SucursalModelAssembler sucursalModelAssembler;

    @GetMapping(value = "/mostrar_sucursales")
    @Operation(
            summary = "Devuelve todas las sucursales",
            description = "Este metodo debe retornar una lista de sucursales, en caso" +
                    "de que no se encuentre nada, retornara una lista vacia"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "se retornaron todas las sucursales", content = @Content(mediaType = MediaTypes.HAL_JSON_VALUE, schema = @Schema(implementation = Sucursal.class)))
    })
    public ResponseEntity<CollectionModel<EntityModel<Sucursal>>> findAll(){

        List<EntityModel<Sucursal>> entityModels = this.sucursalService.findAll()
                .stream()
                .map(sucursalModelAssembler::toModel)
                .toList();
        CollectionModel<EntityModel<Sucursal>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(SucursalControllerV2.class).findAll()).withSelfRel()
        );
        return ResponseEntity.status(200).body(collectionModel);
    }

    @GetMapping("/id/{id}")
    @Operation(
            summary = "Devuelve una sucursal respecto a su id",
            description = "Este metodo debe retornar una sucursal cuando es consultado"+
                    "mediante su Id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se  retorna el cliente encontrado"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error - sucursal con Id no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el id unico de una sucursal",required = true)
    })
    public ResponseEntity<EntityModel<Sucursal>> findById(@PathVariable Long id) {
        EntityModel<Sucursal> entityModel= this.sucursalModelAssembler.toModel(
                sucursalService.findById(id)
        );
        return ResponseEntity.status(200).body(entityModel);
    }

    @PostMapping("/crearSucursal")
    @Operation(
            summary = "Endpoint que me permite guardar una sucursal",
            description = "Este endpoint debo mandar un body con el formato de Sucursal.class "+
                    "y me permitira realizar la creacion de un sucursal"
    )
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Sucursal creada correctamente")})
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Este debe ser Json con los datos de sucursal",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Sucursal.class )

            )
    )
    public ResponseEntity<Sucursal> create(@Valid @RequestBody Sucursal sucursal) {
        Sucursal nuevaSucursal = this.sucursalService.save(sucursal);
        return ResponseEntity.status(201).body(nuevaSucursal);
    }
}
