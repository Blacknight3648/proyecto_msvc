package com.smedinamsvc.resenia.controller;

import com.smedinamsvc.resenia.model.Resenia;
import com.smedinamsvc.resenia.service.ReseniaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/resenias")
@Validated
@Tag(name = "Resenia HATEOAS", description = "CRUD DE RESENIA")
public class ReseniaControllerV2 {
    @Autowired
    private ReseniaService reseniaService;

    @GetMapping
    public ResponseEntity<List<Resenia>> getAll() {
        return ResponseEntity.ok(reseniaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resenia> getById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(reseniaService.findById(id));
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<Resenia>> getByProducto(@PathVariable Long productoId) throws Exception {
        return ResponseEntity.ok(reseniaService.findByProductoId(productoId));
    }

    @PostMapping
    public ResponseEntity<Resenia> create(@RequestBody Resenia resenia) throws Exception {
        return ResponseEntity.ok(reseniaService.save(resenia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resenia> update(@PathVariable Long id, @RequestBody Resenia resenia) throws Exception {
        resenia.setId(id);
        return ResponseEntity.ok(reseniaService.update(resenia));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reseniaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
