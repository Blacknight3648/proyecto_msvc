package com.smedinamsvc_gestionusuarios.service;

import com.smedinamsvc_gestionusuarios.model.Rol;
import java.util.List;
import java.util.Optional;

public interface RolService {

    List<Rol> obtenerTodosLosRoles();
    Optional<Rol> obtenerRolPorId(Long id);
    Rol crearRol(Rol rol);
    Rol actualizarRol(Long id, Rol rol);
    void eliminarRol(Long id);
}

