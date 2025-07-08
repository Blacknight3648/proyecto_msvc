package com.mscv.proveedores.assemblers;

import com.mscv.proveedores.DTO.ProveedorDTO;
import com.mscv.proveedores.controller.ProveedorControllerV2;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProveedorDTOModelAssembler implements RepresentationModelAssembler<ProveedorDTO, EntityModel<ProveedorDTO>> {
    @Override
    public EntityModel<ProveedorDTO> toModel(ProveedorDTO entity) {

        return EntityModel.of(
                entity,
                linkTo(methodOn(ProveedorControllerV2.class).findById(entity.getIdProveedor())).withSelfRel(),
                linkTo(methodOn(ProveedorControllerV2.class)).withRel("Todos los Proveedores")
        );

    }
}
