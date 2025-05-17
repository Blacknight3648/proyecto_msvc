package com.smedinamsvc.resenia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smedinamsvc.resenia.model.Resenia;
import com.smedinamsvc.resenia.service.ReseniaService;

@RestController
@RequestMapping("/api/v1/resenias")
public class ReseniaController {

    @Autowired
    private ReseniaService reseniaService;

    @GetMapping
    public ResponseEntity<List<Resenia>> listarTodas() {
        return ResponseEntity
        .status(200)
        .body(this.reseniaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resenia> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity
        .status(200)
        .body(reseniaService.findById(id));
    }

   @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<Resenia>> buscarPorProducto(@PathVariable Integer productoId) {
        return ResponseEntity
            .status(200)
            .body(this.reseniaService.findByProductoId(productoId));
    }

    @PostMapping
    public ResponseEntity<Resenia> guardar(@RequestBody Resenia resenia) {
        return ResponseEntity
            .status(201)
            .body(this.reseniaService.save(resenia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        this.reseniaService.deleteById(id);
        return ResponseEntity
            .noContent()
            .build();
    }
}

