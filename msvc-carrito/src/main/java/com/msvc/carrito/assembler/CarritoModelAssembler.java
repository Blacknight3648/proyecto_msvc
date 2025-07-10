package com.msvc.carrito.assembler;

import com.msvc.carrito.controller.CarritoControllerV2;
import com.msvc.carrito.model.entity.Carrito;
import org.springframework.hateoas.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

public class CarritoModelAssembler {
    @Override
    public static EntityModel<Carrito> toModel(Carrito entity) {
        return EntityModel.of(

                entity,
                linkTo(methodOn(CarritoControllerV2.class).findAllModels()).withSelfRel(),
                linkTo(methodOn(CarritoControllerV2.class).findByIdCliente(entity.getIdCliente())).withSelfRel(),
                linkTo(methodOn(CarritoControllerV2.class).findByIdVendedor(entity.getIdVendedor())).withSelfRel(),
                Link.of("http://localhost:8026/comprobante"+entity.getIdCarrito()).withSelfRel()
        );
    }
}
