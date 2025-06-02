package com.smedinamsvc.resenia.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.smedinamsvc.resenia.model.Resenia;
import com.smedinamsvc.resenia.service.ReseniaService;

@RestController
@RequestMapping("/api/v1/resenias")
public class ReseniaController {

    private final ReseniaService reseniaService;

    public ReseniaController(ReseniaService reseniaService) {
        this.reseniaService = reseniaService;
    }

    @GetMapping
    public ResponseEntity<List<Resenia>> listarResenias() {
        List<Resenia> resenias = reseniaService.findAll();
        return ResponseEntity.ok(resenias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resenia> obtenerPorId(@PathVariable Long id) {
        Resenia resenia = reseniaService.findById(id);
        return ResponseEntity.ok(resenia);
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<Resenia>> listarPorProducto(@PathVariable Long productoId) {
        List<Resenia> resenias = reseniaService.findByProductoId(productoId);
        return ResponseEntity.ok(resenias);
    }

    @PostMapping
    public ResponseEntity<Resenia> crearResenia(@RequestBody Resenia resenia) {
        Resenia nuevaResenia = reseniaService.save(resenia);
        return ResponseEntity.ok(nuevaResenia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarResenia(@PathVariable Long id) {
        reseniaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //Actualizar las resenias
    @PutMapping({"/{id}"})
    public ResponseEntity<Resenia> actualizarResenia (@RequestBody Resenia resenia){
        Resenia actualizarResenia = reseniaService.updateById(resenia);
        return ResponseEntity.ok(actualizarResenia);
    }
}


