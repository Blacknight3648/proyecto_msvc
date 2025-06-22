package com.smedinamsvc.resenia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.smedinamsvc.resenia.model.Resenia;
import com.smedinamsvc.resenia.service.ReseniaService;

@RestController
@RequestMapping("/api/v1/resenias")

public class ReseniaController {

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

    @PutMapping
    public ResponseEntity<Resenia> update(@RequestBody Resenia resenia) throws Exception {
        return ResponseEntity.ok(reseniaService.update(resenia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reseniaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}


