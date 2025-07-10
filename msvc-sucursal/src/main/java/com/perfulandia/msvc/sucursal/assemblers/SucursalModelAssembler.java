package com.perfulandia.msvc.sucursal.assemblers;


import com.perfulandia.msvc.sucursal.controllers.SucursalControllerV2;
import com.perfulandia.msvc.sucursal.models.Sucursal;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


public class SucursalModelAssembler implements RepresentationModelAssembler<Sucursal, EntityModel<Sucursal>>{

    @Override
    public EntityModel<Sucursal> toModel(Sucursal entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(SucursalControllerV2.class).findById(entity.getIdSucursal())).withSelfRel(),
                Link.of("http://localhost:8025/sucursales/id"+entity.getIdSucursal()).withSelfRel()
        );
    }
}
