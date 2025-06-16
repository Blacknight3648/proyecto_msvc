package com.smedinamsvc.resenia.controller;

import java.util.List;

import com.smedinamsvc.resenia.exceptions.ReseniaExceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.smedinamsvc.resenia.model.Resenia;
import com.smedinamsvc.resenia.service.ReseniaService;

@RestController
@RequestMapping("/api/v1/resenias")

    /*ACÁ LA API INTERACTUA CON LA BASE DE DATOS MEDIANTE LOS ENDPOINTS:
     - GET
     - POST
     - PUT
     - DELETE
    */

public class ReseniaController {

    private final ReseniaService reseniaService;

    public ReseniaController(ReseniaService reseniaService) {
        this.reseniaService = reseniaService;
    }

    //READ
    @GetMapping
    public ResponseEntity<List<Resenia>> listarResenias() {
        List<Resenia> resenias = reseniaService.findAll();
        return ResponseEntity.ok(resenias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resenia> obtenerPorId(@PathVariable Long id) throws ReseniaExceptions {
        Resenia resenia = reseniaService.findById(id);
        return ResponseEntity.ok(resenia);
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<Resenia>> listarPorProducto(@PathVariable Long productoId) throws ReseniaExceptions {
        List<Resenia> resenias = reseniaService.findByProductoId(productoId);
        return ResponseEntity.ok(resenias);
    }

    //CREATE
    @PostMapping
    public ResponseEntity<Resenia> crearResenia(@RequestBody Resenia resenia) throws ReseniaExceptions {
        Resenia nuevaResenia = reseniaService.save(resenia);
        return ResponseEntity.ok(nuevaResenia);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarResenia(@PathVariable Long id) {
        reseniaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Resenia> actualizarResenia(@PathVariable Long id, @RequestBody Resenia resenia) {
        resenia.setId(id); // Asegura que se use el ID del path
        Resenia actualizarResenia = reseniaService.updateById(resenia);
        return ResponseEntity.ok(actualizarResenia);
    }
}


