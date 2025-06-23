package com.perfulandia.msvc.comprobante.venta.assemblers;

import com.perfulandia.msvc.comprobante.venta.controllers.ComprobanteController;
import com.perfulandia.msvc.comprobante.venta.controllers.ComprobanteControllerV2;
import com.perfulandia.msvc.comprobante.venta.models.entities.Comprobante;

import org.springframework.hateoas.*;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ComprobanteModelAssembler implements RepresentationModelAssembler<Comprobante, EntityModel<Comprobante>> {

    @Override
    public EntityModel<Comprobante> toModel(Comprobante entity) {
        return EntityModel.of(

                entity,
                linkTo(methodOn(ComprobanteControllerV2.class).findById(entity.getIdComprobante())).withSelfRel(),
                linkTo(methodOn(ComprobanteControllerV2.class).findAllModels()).withSelfRel(),
                linkTo(methodOn(ComprobanteControllerV2.class).findByIdCarrito(entity.getIdCarrito())).withSelfRel(),
                linkTo(methodOn(ComprobanteControllerV2.class).findByIdCliente(entity.getIdCliente())).withSelfRel(),
                linkTo(methodOn(ComprobanteControllerV2.class).findByIdSucursal(entity.getIdSucursal())).withSelfRel(),
                linkTo(methodOn(ComprobanteControllerV2.class).findByIdVendedor(entity.getIdVendedor())).withSelfRel(),
                Link.of("http://localhost:8024/comprobante"+entity.getIdComprobante()).withSelfRel()
        );
    }
}
