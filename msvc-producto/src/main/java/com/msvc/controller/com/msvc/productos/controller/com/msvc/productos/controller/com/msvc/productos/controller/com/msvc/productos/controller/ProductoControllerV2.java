package com.msvc.controller.com.msvc.productos.controller.com.msvc.productos.controller.com.msvc.productos.controller.com.msvc.productos.controller;

import com.msvc.assemblers.ProductoDTOModelAssambler;
import com.msvc.assemblers.ProductoModelAssambler;
import com.msvc.dtos.ErrorDTO;
import com.msvc.dtos.ProductoDTO;
import com.msvc.model.entity.ProductoModel;
import com.msvc.services.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/productos")
@Validated
@Tag(name= "Productos", description = "Esta seccion contiene los CRUD de producto")
public class ProductoControllerV2 {

    private final ProductoService productoService;
    private final ProductoModelAssambler productoModelAssambler;

    private ProductoDTOModelAssambler productoDTOModelAssambler;



    // Inyección por constructor
    public ProductoControllerV2(ProductoService productoService, ProductoModelAssambler productoModelAssambler) {
        this.productoService = productoService;
        this.productoModelAssambler = productoModelAssambler;
    }

    @GetMapping("/mostrar_producto")
    @Operation(
            summary = "Devuelve todos los productos",
            description = "Este metodo debe retornar una lista de productos, en caso"+
                    " de que no se encuente nada, retorna una lista vacia"
    )
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Se retornan todos los productos")
    })
    public ResponseEntity<CollectionModel<EntityModel<ProductoModel>>> findAll() {
        List<EntityModel<ProductoModel>> entityModels = this.productoService.findAll().stream().map(productoModelAssambler::toModel).toList();
        CollectionModel<EntityModel<ProductoModel>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(ProductoControllerV2.class).findAll()).withSelfRel()
        );
        return ResponseEntity.status(200).body(collectionModel);    }

    @GetMapping("/id/{id}")
    @Operation(
            summary = "Devuelve un producto respecto a su id",
            description = "Este metodo debe retornar un producto cuando es consultado" +
                    " mediante su id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retorna un producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Error - producto con id no existe",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ErrorDTO.class)))
    })
    @Parameters(value = {
            @Parameter(name = "id",description = "Este es el id unico de un Producto", required = true)
    })
    public ResponseEntity<EntityModel<ProductoModel>> findById(@PathVariable Long id) {

        EntityModel<ProductoModel> entityModel = this.productoModelAssambler.toModel(productoService.findById(id));
        return ResponseEntity.status(200).body(entityModel);
    }

    @PostMapping("/crearPeoducto")
    @Operation(
            summary = "Endpoint que permite guardar un producto",
            description = "Este endpoint debe mandar un body con el formato de Producto.class "+
                    "y me permitira realizar la creacion de un producto"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado correctamente")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = ("Este debe ser Json con los datos de producto"),
            content = @Content(
                    mediaType = "applicattion/json",
                    schema = @Schema(implementation = ProductoModel.class)
            )
    )
    public ResponseEntity<ProductoModel> create(@Valid @RequestBody ProductoModel productoModel){

        ProductoModel nuevoProductoModel = this.productoService.save(productoModel);
        return ResponseEntity.status(201).body(nuevoProductoModel);

    }

    @PutMapping("/modificar/{id}")
    @Operation(
            summary = "Endpoint que permite modificar un producto",
            description = "Este endpoint recibe el ID del prodcuto por la URL y un cuerpo en formato ProductoDTO con los datos modificados."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Producto modificado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida o datos incorrectos")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Debe enviar un Json con los datos modificados del producto",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProductoDTO.class)
            )
    )
    public ResponseEntity<ProductoModel> update(@PathVariable Long id, @Valid @RequestBody ProductoDTO productoDTO) {

        ProductoModel productoModificado = productoService.update(id, productoDTO);
        return ResponseEntity.status(202).body(productoModificado);

    }
    @DeleteMapping("/eliminar_producto/{id}")
    @Operation(
            summary = "Eliminar un producto por ID",
            description = "Este endpoint permite eliminar un producto especifico utilizando su Id "+
                    "devuelve el producto eliminado si la operacion fue exitosa."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Producto eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "400", description = "id invalido"),

    })
    public ResponseEntity<ProductoModel> deleteById(@PathVariable Long id){

        ProductoModel productoModel = productoService.deleteById(id);
        return ResponseEntity.status(202).body(productoModel);

    }
}
