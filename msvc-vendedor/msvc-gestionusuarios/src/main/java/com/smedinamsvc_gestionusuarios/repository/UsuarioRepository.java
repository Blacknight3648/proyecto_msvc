package com.smedinamsvc_gestionusuarios.repository;

import com.smedinamsvc_gestionusuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByRutUsuario(Integer rutUsuario);
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByCorreoElectronico(String correoElectronico);
    List<Usuario> findAllByActivoFalse();
}


