package com.smedinamsvc.resenia.controller;

import com.smedinamsvc.resenia.assemblers.ReseniaModelAssembler;
import com.smedinamsvc.resenia.model.Resenia;
import com.smedinamsvc.resenia.service.ReseniaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/resenias")
@Validated
@Tag(name = "Resenia HATEOAS", description = "CRUD DE RESENIA")
public class ReseniaControllerV2 {

    @Autowired
    private ReseniaService reseniaService;

    @Autowired
    private ReseniaModelAssembler assembler;

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Resenia>> getById(@PathVariable Long id) throws Exception {
        Resenia resenia = reseniaService.findById(id);
        return ResponseEntity.ok(assembler.toModel(resenia));
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Resenia>>> getAll() {
        List<EntityModel<Resenia>> resenias = reseniaService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(resenias,
                        linkTo(methodOn(ReseniaControllerV2.class).getAll()).withSelfRel())
        );
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<CollectionModel<EntityModel<Resenia>>> getByProducto(@PathVariable Long productoId) throws Exception {
        List<EntityModel<Resenia>> resenias = reseniaService.findByProductoId(productoId).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(resenias,
                        linkTo(methodOn(ReseniaControllerV2.class).getByProducto(productoId)).withSelfRel())
        );
    }

    @PostMapping
    public ResponseEntity<EntityModel<Resenia>> create(@RequestBody Resenia resenia) throws Exception {
        Resenia nueva = reseniaService.save(resenia);
        return ResponseEntity.ok(assembler.toModel(nueva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Resenia>> update(@PathVariable Long id, @RequestBody Resenia resenia) throws Exception {
        resenia.setId(id);
        Resenia actualizada = reseniaService.update(resenia);
        return ResponseEntity.ok(assembler.toModel(actualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reseniaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

