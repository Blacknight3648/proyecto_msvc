package com.smedinamsvc_gestionusuarios.service;

import com.smedinamsvc_gestionusuarios.DTO.UsuarioDTO;
import java.util.List;

public interface UsuarioService {
    List<UsuarioDTO> obtenerTodos();
    UsuarioDTO obtenerPorId(Long id);
    UsuarioDTO crear(UsuarioDTO usuarioDTO);
    UsuarioDTO actualizar(Long id, UsuarioDTO usuarioDTO);
    void suspender(Long id);
    
    // Métodos opcionales:
    List<UsuarioDTO> obtenerSuspendidos();
    void reactivar(Long id);
}


