package com.msvc.vendedor.assamblers;

import com.msvc.vendedor.controllers.VendedorControllerV2;
import com.msvc.vendedor.models.Vendedor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

public class VendedorModelAssambler implements RepresentationModelAssembler<Vendedor, EntityModel<Vendedor>> {
    @Override
    public EntityModel<Vendedor> toModel(Vendedor entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(VendedorControllerV2.class).findById(entity.getIdVendedor())).withSelfRel(),
                linkTo(methodOn(VendedorControllerV2.class).findAll()).withSelfRel(),
                linkTo(methodOn(VendedorControllerV2.class).findByRunVendedor(entity.getRunVendedor())).withSelfRel(),
                Link.of("http://localjost:8023/vendedor"+entity.getIdVendedor()).withSelfRel()
        );
    }
}
