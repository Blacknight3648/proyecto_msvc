package com.msvc.vendedor.controllers;

import com.msvc.vendedor.dtos.ErrorDTO;
import com.msvc.vendedor.dtos.VendedorDTO;
import com.msvc.vendedor.models.Vendedor;
import com.msvc.vendedor.services.VendedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.hibernate.annotations.AttributeAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vendedor")
@Validated
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @GetMapping("/mostrar_vendedores")
    @Operation(
            summary = "Devuelve todos los vendedores",
            description = "Este metodo debe retornar una lista de vendedores, en caso"+
                    " de que no se encuente nada, retorna una lista vacia"
    )
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Se retornan todos los vendedores")
    })
    public ResponseEntity<List<Vendedor>>findAll(){
        List<Vendedor> vendedores = this.vendedorService.findAll();
        return ResponseEntity.status(200).body(vendedores);
    }

    @GetMapping("/id/{id}")
    @Operation(
            summary = "Devuelve un vendedor respecto a su id",
            description = "Este metodo debe retornar un Vendedor cuando es consultado" +
                    " mediante su id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retorna un vendedor encontrado"),
            @ApiResponse(responseCode = "404", description = "Error - vendedor con id no existe",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ErrorDTO.class)))
    })
    @Parameters(value = {
            @Parameter(name = "id",description = "Este es el id unico de un cliente", required = true)
    })
    public ResponseEntity<Vendedor> findById(@PathVariable Long id){

        Vendedor vendedor = this.vendedorService.findById(id);
        return ResponseEntity.status(200).body(vendedor);

    }

    @GetMapping("/run/{runVendedor}")
    @Operation(

            summary = "Devuelve un cliente con respecto a su rut",
            description = "Este metodo debe retornar un Vendedor cuando es consultado"+
                    " mediante su rut"

    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retorna el vendedor encontrado"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error - Vendedor con este rut no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el rut unico de un cliente", required = true)
    })
    public ResponseEntity<VendedorDTO> findByRunVendedor(String runVendedor){

        VendedorDTO vendedorDTO=this.vendedorService.findByRunVendedor(runVendedor);
        return ResponseEntity.status(200).body(vendedorDTO);

    }


    @PostMapping("/crearVendedor")
    @Operation(
            summary = "Endpoint que permite guardar un vendedor",
            description = "Este endpoint debo mandar un body con el formato de Vendedor.class "+
                    "y me permitira realizar la creacion de un vendedor"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vendedor creado correctamente")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = ("Este debe ser Json con los datos de vendedor"),
            content = @Content(
                    mediaType = "applicattion/json",
                    schema = @Schema(implementation = Vendedor.class)
            )
    )
    public ResponseEntity<Vendedor> create(@Valid @RequestBody Vendedor vendedor){

        Vendedor nuevVendedor = this.vendedorService.save(vendedor);
        return ResponseEntity.status(201).body(nuevVendedor);

    }

    @PutMapping("/suspender/{id}")
    @Operation(
            summary = "Endpoint que permite suspender un vendedor",
            description = "Este endpoint recibe el ID del vendedor por la URL y y permite modificar el estado de cuenta a traves de un VendedorDTO para suspender al vendedor correspondiente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Vendedor suspendido correctamente"),
            @ApiResponse(responseCode = "404", description = "Vendedor no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos en la solicitud")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Debe enviar un Json con los datos requeridos para suspender el cliente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = VendedorDTO.class)
            )
    )
    public ResponseEntity<Vendedor> suspend(@PathVariable Long id, @Valid @RequestBody VendedorDTO vendedorDTO) {

        Vendedor vendedorSuspendido = vendedorService.suspend(id, vendedorDTO);
        return ResponseEntity.status(202).body(vendedorSuspendido);

    }

    @PutMapping("/modificar/{id}")
    @Operation(
            summary = "Endpoint que permite modificar un vendedor",
            description = "Este endpoint recibe el ID del vendedor por la URL y un cuerpo en formato VendedorDTO con los datos modificados."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Vendedor modificado correctamente"),
            @ApiResponse(responseCode = "404", description = "Vendedor no encontrado"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida o datos incorrectos")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Debe enviar un Json con los datos modificados del cliente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = VendedorDTO.class)
            )
    )
    public ResponseEntity<Vendedor> update(@PathVariable Long id, @Valid @RequestBody VendedorDTO vendedorDTO) {

        Vendedor vendedorModificado = vendedorService.update(id, vendedorDTO);
        return ResponseEntity.status(202).body(vendedorModificado);

    }

    @DeleteMapping("/eliminar_vendedor/{id}")
    @Operation(
            summary = "Eliminar un cliente por ID",
            description = "Este endpoint permite eliminar un vendedor especifico utilizando su Id "+
                    "devuelve el cliente eliminado si la operacion fue exitosa."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Vendedor eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Vendedor no encontrado"),
            @ApiResponse(responseCode = "400", description = "id invalido"),

    })
    public ResponseEntity<Vendedor> deleteById(@PathVariable Long id){

        Vendedor vendedor = vendedorService.deleteById(id);
        return ResponseEntity.status(202).body(vendedor);

    }

}
