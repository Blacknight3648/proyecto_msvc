package com.msvc.clientes.assamblers;

import com.msvc.clientes.DTO.ClienteDTO;
import com.msvc.clientes.controllers.ClienteControllerV2;
import com.msvc.clientes.models.Cliente;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ClienteModelAssamabler implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {

    @Override
    public EntityModel<Cliente> toModel(Cliente entity){

        return EntityModel.of(

                entity,
                linkTo(methodOn(ClienteControllerV2.class).findById(entity.getIdCliente())).withSelfRel(),
                linkTo(methodOn(ClienteControllerV2.class).findAll()).withSelfRel(),
                linkTo(methodOn(ClienteControllerV2.class).findByRun(entity.getRunCliente())).withSelfRel(),
                Link.of("http://localhost:8020/cliente"+entity.getIdCliente()).withSelfRel()

        );

    }

}
