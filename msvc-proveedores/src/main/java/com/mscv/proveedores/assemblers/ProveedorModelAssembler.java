package com.mscv.proveedores.assemblers;

import com.mscv.proveedores.controller.ProveedorControllerV2;
import com.mscv.proveedores.model.Proveedor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Este assembler transforma objetos Proveedor en EntityModel<Proveedor>,
 * agregando links HATEOAS para operaciones comunes como ver, actualizar y eliminar.
 */

@Component // Para que Spring lo detecte como un bean
public class ProveedorModelAssembler implements RepresentationModelAssembler<Proveedor, EntityModel<Proveedor>> {

    @Override
    public EntityModel<Proveedor> toModel(Proveedor entity) {
        return EntityModel.of(
                entity,

                // Link al recurso actual (self)
                linkTo(methodOn(ProveedorControllerV2.class).findById(entity.getIdProveedor())).withSelfRel(),

                // Link para obtener todos los proveedores
                linkTo(methodOn(ProveedorControllerV2.class).findAll()).withRel("proveedores"),

                // Link para actualizar el proveedor actual
                linkTo(methodOn(ProveedorControllerV2.class).update(entity.getIdProveedor(), null)).withRel("update"),

                // Link para eliminar el proveedor actual
                linkTo(methodOn(ProveedorControllerV2.class).delete(entity.getIdProveedor())).withRel("delete")
        );
    }
}

