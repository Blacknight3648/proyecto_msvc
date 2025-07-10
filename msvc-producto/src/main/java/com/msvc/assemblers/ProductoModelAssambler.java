package com.msvc.assemblers;


import com.msvc.controller.com.msvc.productos.controller.com.msvc.productos.controller.com.msvc.productos.controller.com.msvc.productos.controller.ProductoControllerV2;
import com.msvc.model.entity.ProductoModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductoModelAssambler implements RepresentationModelAssembler<ProductoModel, EntityModel<ProductoModel>> {

    @Override
    public EntityModel<ProductoModel> toModel(ProductoModel entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(ProductoControllerV2.class).findById(entity.getIdProducto())).withSelfRel(),
              /*  linkTo(methodOn(ProductoControllerV2.class).findAll()).withSelfRel(),
                linkTo(methodOn(ProductoControllerV2.class).findById(entity.getIdCliente())).withSelfRel(),*/
                Link.of("http://localhost:8022/producto/id/"+entity.getIdProducto()).withSelfRel()
        );
    }


}
