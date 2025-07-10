package com.msvc.assemblers;


import com.msvc.controller.com.msvc.productos.controller.com.msvc.productos.controller.com.msvc.productos.controller.com.msvc.productos.controller.ProductoControllerV2;
import com.msvc.dtos.ProductoDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductoDTOModelAssambler implements RepresentationModelAssembler <ProductoDTO, EntityModel<ProductoDTO>> {

    @Override
    public EntityModel<ProductoDTO> toModel(ProductoDTO entity){

        return EntityModel.of(
                entity,
                linkTo(methodOn(ProductoControllerV2.class).findById(entity.getIdProducto())).withSelfRel()

        );
    }
}
