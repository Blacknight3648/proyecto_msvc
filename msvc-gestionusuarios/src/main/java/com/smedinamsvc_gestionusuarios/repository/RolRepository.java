package com.smedinamsvc_gestionusuarios.repository;

import com.smedinamsvc_gestionusuarios.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    // Buscar un rol por su nombre (ej: "ADMIN", "USER", etc.)
    Optional<Rol> findByNombre(String nombre);

    // Verifica si un rol existe por su nombre
    boolean existsByNombre(String nombre);

    // Buscar todos los roles que contengan una cadena en el nombre (útil para filtros)
    List<Rol> findByNombreContainingIgnoreCase(String nombre);
}


