package com.msvc.vendedor.assamblers;

import com.msvc.vendedor.controllers.VendedorControllerV2;
import com.msvc.vendedor.dtos.VendedorDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import javax.swing.text.html.parser.Entity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

public class VendedorDTOModelAssambler implements RepresentationModelAssembler <VendedorDTO, EntityModel<VendedorDTO>>{

    @Override
    public EntityModel<VendedorDTO> toModel(VendedorDTO entity){

        return EntityModel.of(
                entity,
                linkTo(methodOn(VendedorControllerV2.class).findByRunVendedor(entity.getRunVendedor())).withSelfRel()
        );

    }
}
