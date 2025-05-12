package com.smedinamsvc_gestionusuarios.service.impl;

import com.smedinamsvc_gestionusuarios.DTO.UsuarioDTO;
import com.smedinamsvc_gestionusuarios.model.Rol;
import com.smedinamsvc_gestionusuarios.model.Usuario;
import com.smedinamsvc_gestionusuarios.repository.UsuarioRepository;
import com.smedinamsvc_gestionusuarios.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioDTO> obtenerTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public UsuarioDTO crear(UsuarioDTO dto) {
        Usuario usuario = mapToEntity(dto);
        return mapToDTO(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDTO actualizar(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setNombres(dto.getNombres());
        usuario.setApellidoPaterno(dto.getApellidoPaterno());
        usuario.setApellidoMaterno(dto.getApellidoMaterno());
        usuario.setCorreoElectronico(dto.getCorreoElectronico());
        usuario.setFechaNacimiento(dto.getFechaNacimiento());
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setRutUsuario(dto.getRutUsuario());
        return mapToDTO(usuarioRepository.save(usuario));
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO mapToDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getRutUsuario(),
                usuario.getNombres(),
                usuario.getApellidoPaterno(),
                usuario.getApellidoMaterno(),
                usuario.getFechaNacimiento(),
                usuario.getNombreUsuario(),
                usuario.getCorreoElectronico(),
                usuario.getRoles().stream().map(Rol::getNombreRol).collect(Collectors.toSet())
        );
    }

    private Usuario mapToEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setRutUsuario(dto.getRutUsuario());
        usuario.setNombres(dto.getNombres());
        usuario.setApellidoPaterno(dto.getApellidoPaterno());
        usuario.setApellidoMaterno(dto.getApellidoMaterno());
        usuario.setFechaNacimiento(dto.getFechaNacimiento());
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setCorreoElectronico(dto.getCorreoElectronico());
        usuario.setContrasenia("SIN_ENCRIPTAR"); // solo mientras pruebas
        usuario.setRoles(new HashSet<>()); // asignación posterior
        return usuario;
    }
}

