package com.msvc.clientes.assamblers;

import com.msvc.clientes.DTO.ClienteDTO;
import com.msvc.clientes.controllers.ClienteControllerV2;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ClienteDTOModelAssembler  implements RepresentationModelAssembler<ClienteDTO, EntityModel<ClienteDTO>> {
    @Override
    public EntityModel<ClienteDTO> toModel(ClienteDTO entity){

        return EntityModel.of(

                entity,
                linkTo(methodOn(ClienteControllerV2.class).findByRun(entity.getRunCliente())).withSelfRel()

        );
    }
}
