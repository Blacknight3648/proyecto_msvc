package com.smedinamsvc_gestionusuarios.controller;

import com.smedinamsvc_gestionusuarios.model.Rol;
import com.smedinamsvc_gestionusuarios.service.RolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }
 
    @GetMapping
    public ResponseEntity<List<Rol>> obtenerTodosLosRoles() {
        return ResponseEntity.ok(rolService.obtenerTodosLosRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> obtenerRolPorId(@PathVariable Long id) {
        Optional<Rol> rol = rolService.obtenerRolPorId(id);
        return rol.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Rol> crearRol(@RequestBody Rol rol) {
        Rol nuevoRol = rolService.crearRol(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRol);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizarRol(@PathVariable Long id, @RequestBody Rol rol) {
        try {
            Rol rolActualizado = rolService.actualizarRol(id, rol);
            return ResponseEntity.ok(rolActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable Long id) {
        try {
            rolService.eliminarRol(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

