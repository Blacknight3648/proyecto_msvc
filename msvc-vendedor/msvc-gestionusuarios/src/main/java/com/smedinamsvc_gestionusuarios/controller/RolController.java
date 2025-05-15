package com.smedinamsvc_gestionusuarios.controller;

import com.smedinamsvc_gestionusuarios.model.Rol;
import com.smedinamsvc_gestionusuarios.service.RolService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/roles")
@Validated
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public ResponseEntity<List<Rol>> findAll() {
        return ResponseEntity
                .status(200)
                .body(rolService.obtenerTodosLosRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> findById(@PathVariable Long id) {
        Optional<Rol> rol = rolService.obtenerRolPorId(id);
        return rol.map(value -> ResponseEntity
                        .status(200)
                        .body(value))
                .orElseGet(() -> ResponseEntity
                        .status(404)
                        .build());
    }

    @PostMapping
    public ResponseEntity<Rol> create(@Valid @RequestBody Rol rol) {
        return ResponseEntity
                .status(201)
                .body(rolService.crearRol(rol));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> update(@PathVariable Long id, @Valid @RequestBody Rol rol) {
        try {
            return ResponseEntity
                    .status(200)
                    .body(rolService.actualizarRol(id, rol));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            rolService.eliminarRol(id);
            return ResponseEntity
                    .noContent()
                    .build();
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(404)
                    .build();
        }
    }
}


