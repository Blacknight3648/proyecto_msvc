package com.smedinamsvc.resenia.assemblers;

import com.smedinamsvc.resenia.controller.ReseniaControllerV2;
import com.smedinamsvc.resenia.dtos.ReseniaDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReseniaDTOModelAssembler implements RepresentationModelAssembler<ReseniaDTO, EntityModel<ReseniaDTO>> {

    @Override
    public EntityModel<ReseniaDTO> toModel(ReseniaDTO reseniaDTO) {
        return EntityModel.of(
                reseniaDTO,
                linkTo(ReseniaControllerV2.class).slash(reseniaDTO.getId()).withSelfRel(),
                linkTo(methodOn(ReseniaControllerV2.class).getAll()).withRel("Todas las Resenias")
        );
    }
}

