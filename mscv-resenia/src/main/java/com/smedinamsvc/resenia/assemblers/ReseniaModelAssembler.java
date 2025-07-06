package com.smedinamsvc.resenia.assemblers;

import com.smedinamsvc.resenia.controller.ReseniaControllerV2;
import com.smedinamsvc.resenia.model.Resenia;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Assembler para convertir Resenia a EntityModel<Resenia> con enlaces HATEOAS
 */
@Component
public class ReseniaModelAssembler implements RepresentationModelAssembler<Resenia, EntityModel<Resenia>> {

    @Override
    public EntityModel<Resenia> toModel(Resenia resenia) {
        try {
            return EntityModel.of(
                    resenia,
                    linkTo(methodOn(ReseniaControllerV2.class).getById(resenia.getId())).withSelfRel(),
                    linkTo(methodOn(ReseniaControllerV2.class).getAll()).withRel("resenias")
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
