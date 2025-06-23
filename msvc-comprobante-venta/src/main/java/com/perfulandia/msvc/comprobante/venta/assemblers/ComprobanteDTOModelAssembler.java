package com.perfulandia.msvc.comprobante.venta.assemblers;

import com.perfulandia.msvc.comprobante.venta.controllers.ComprobanteControllerV2;
import com.perfulandia.msvc.comprobante.venta.dtos.ComprobanteDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Component
public class ComprobanteDTOModelAssembler implements RepresentationModelAssembler<ComprobanteDTO, EntityModel<ComprobanteDTO>> {

    @Override
    public EntityModel<ComprobanteDTO> toModel(ComprobanteDTO entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(ComprobanteControllerV2.class).findAll()).withSelfRel()
        );
    }
}
