package com.perfulandia.msvc.comprobante.venta.controllers;

import com.perfulandia.msvc.comprobante.venta.dtos.ComprobanteDTO;
import com.perfulandia.msvc.comprobante.venta.dtos.ErrorDTO;
import com.perfulandia.msvc.comprobante.venta.models.Cliente;
import com.perfulandia.msvc.comprobante.venta.models.entities.Comprobante;
import com.perfulandia.msvc.comprobante.venta.services.ComprobanteService;
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
@RequestMapping("/api/v1/comprobantes")
@Validated
@Tag(name = "Comprobante", description = "Esta seccion contiene los CRUD de comprobante")
public class ComprobanteController {

    @Autowired
    private ComprobanteService comprobanteService;

    @GetMapping("/mostrar_comprobantes")
    @Operation(
            summary = "Devuelve todos los comprobantes, ",
            description = "Este metodo debe retornar una lista de comprobantes, en caso"+
                    " de que no se encuentre nada retorna una lista vacía"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retornaron todos los comprobantes")
    })
    public ResponseEntity<List<ComprobanteDTO>> findAll(){
        List<ComprobanteDTO> comprobantes = this.comprobanteService.findAll();
        return ResponseEntity.status(200).body(comprobantes);
    }

    @GetMapping("/mostrar_comprobantes_models")
    @Operation(
            summary = "Devuelve todos los comprobantes, ",
            description = "Este metodo debe retornar una lista de comprobantes, en caso"+
                    " de que no se encuentre nada retorna una lista vacía"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retornaron todos los comprobantes")
    })
    public ResponseEntity<List<Comprobante>> findAllModels(){
        List<Comprobante> comprobantes = this.comprobanteService.findAllModels();
        return ResponseEntity.status(200).body(comprobantes);
    }

    @GetMapping("id/{id}")
    @Operation(
            summary = "Devuelve un comprobante respecto a su id",
            description = "Este metodo debe retornar un comprobante cuando es consultado mediante su id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retorna el comprobante encontrado"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error - comprobante con ID no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el id unico de un comprobante",required = true)
    })
    public ResponseEntity<Comprobante> findById(@PathVariable Long id){
        Comprobante comprobante = this.comprobanteService.findById(id);
        return ResponseEntity.status(200).body(comprobante);
    }

    @PostMapping("/crearComprobante")
    @Operation(
            summary = "Endpoint que permita guardar un comprobante",
            description = "Este endpoint debe recibir un body con el formato de Comprobante y permitira realizar la creacion de un comprobante"
    )
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Comprobante creado correctamente")})
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Este debe ser Json con los datos de comprobante",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Comprobante.class)
            )
    )
    public ResponseEntity<Comprobante> save(@RequestBody @Valid Comprobante comprobante){
        Comprobante nuevoComprobante = this.comprobanteService.save(comprobante);
        return ResponseEntity.status(201).body(nuevoComprobante);
    }

    @GetMapping("/cliente/{id}")
    @Operation(
            summary = "Devuelve los comprobantes segun el ID del cliente",
            description = "Este metodo debe retornar el historial de un cliente mediante su id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retorna el historial de comprobantes del cliente"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error - Cliente con ID no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el id unico de un cliente", required = true)
    })
    public ResponseEntity<List<Comprobante>> findByIdCliente(@PathVariable Long id){
        return ResponseEntity.status(200).body(this.comprobanteService.findByClienteId(id));
    }

    @GetMapping("/vendedor/{id}")
    @Operation(
            summary = "Devuelve los comprobantes segun el ID del vendedor",
            description = "Este metodo debe retornar el historial de un vendedor mediante su id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retorna el historial de comprobantes del vendedor"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error - Vendedor con ID no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el id unico de un vendedor", required = true)
    })
    public ResponseEntity<List<Comprobante>> findByIdVendedor(@PathVariable Long id){
        return ResponseEntity.status(200).body(this.comprobanteService.findByVendedorId(id));
    }

    @GetMapping("/sucursal/{id}")
    @Operation(
            summary = "Devuelve los comprobantes segun el ID del sucursal",
            description = "Este metodo debe retornar el historial de una sucursal mediante su id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retorna el historial de comprobantes de la sucursal"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error - Sucursal con ID no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el id unico de un sucursal", required = true)
    })
    public ResponseEntity<List<Comprobante>> findByIdSucursal(@PathVariable Long id){
        return ResponseEntity.status(200).body(this.comprobanteService.findBySucursalId(id));
    }

    @GetMapping("/carrito/{id}")
    @Operation(
            summary = "Devuelve el comprobante segun el ID del carrito",
            description = "Este metodo debe retornar el comprobante de un carrito mediante su id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retorna el comprobante de carrito"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error - Carrito con ID no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el id unico de un carrito", required = true)
    })
    public ResponseEntity<List<Comprobante>> findByIdCarrito(@PathVariable Long id){
        return ResponseEntity.status(200).body(this.comprobanteService.findByCarritoId(id));
    }

    @DeleteMapping("eliminar_comprobante/{id}")
    @Operation(
            summary = "Eliminar un coprobante por ID",
            description = "Es te endpoint permite eliminar un comprobante especifico utilizando su ID." +
                    "Devuelve el cliente eliminado si la operación fue existosa."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Comprobante eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Comprobante no encontrado"),
            @ApiResponse(responseCode = "400", description = "ID invalido")
    })
    public ResponseEntity<Comprobante> deleteById(@PathVariable Long id){

        Comprobante comprobante = comprobanteService.deleteById(id);
        return ResponseEntity.status(202).body(comprobante);
    }


}
