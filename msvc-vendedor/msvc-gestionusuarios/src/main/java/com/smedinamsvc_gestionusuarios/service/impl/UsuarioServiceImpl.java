package com.smedinamsvc_gestionusuarios.service.impl;

import com.smedinamsvc_gestionusuarios.DTO.UsuarioDTO;
import com.smedinamsvc_gestionusuarios.DTO.RequestDTO.UsuarioRequestDTO;
import com.smedinamsvc_gestionusuarios.model.Rol;
import com.smedinamsvc_gestionusuarios.model.Usuario;
import com.smedinamsvc_gestionusuarios.repository.UsuarioRepository;
import com.smedinamsvc_gestionusuarios.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Inyecta automáticamente los repositorios vía constructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Devuelve todos los usuarios registrados en el sistema
    @Override
    public List<UsuarioDTO> obtenerTodos() {
        return usuarioRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Busca un usuario por su ID, si no existe lanza excepción
    @Override
    public UsuarioDTO obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con ID: " + id));
    }

    // Crea un nuevo usuario a partir del DTO recibido
    @Override
    public UsuarioDTO crear(UsuarioRequestDTO requestDTO) {
        Usuario usuario = mapToEntity(requestDTO);
        return mapToDTO(usuarioRepository.save(usuario));
    }

    // Actualiza los datos de un usuario existente
    @Override
    public UsuarioDTO actualizar(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con ID: " + id));

        usuario.setRutUsuario(dto.getRutUsuario());
        usuario.setNombres(dto.getNombres());
        usuario.setFechaNacimiento(dto.getFechaNacimiento());
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setCorreoElectronico(dto.getCorreoElectronico());

        return mapToDTO(usuarioRepository.save(usuario));
    }

    // Marca un usuario como inactivo (suspendido)
    @Override
    public void suspender(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con ID: " + id));
        usuario.setActivo(false);
        usuarioRepository.save(usuario);
    }

    // Opcional: Retorna todos los usuarios que están suspendidos (inactivos)
    @Override
    public List<UsuarioDTO> obtenerSuspendidos() {
        return usuarioRepository.findAll().stream()
                .filter(usuario -> !usuario.isActivo())
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Reactiva un usuario previamente suspendido
    @Override
    public void reactivar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con ID: " + id));
        usuario.setActivo(true);
        usuarioRepository.save(usuario);
    }

    // Mapea de entidad a DTO (para enviar al frontend solo los datos necesarios)
    private UsuarioDTO mapToDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getRutUsuario(),
                usuario.getNombres(),
                usuario.getFechaNacimiento(),
                usuario.getNombreUsuario(),
                usuario.getCorreoElectronico(),
                usuario.getContrasenia(),
                usuario.getRoles().stream().map(Rol::getNombreRol).collect(Collectors.toSet()),
                usuario.isActivo()
        );
    }

    // Mapea de DTO a entidad (para guardar en base de datos)
    private Usuario mapToEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setRutUsuario(dto.getRutUsuario());
        usuario.setNombres(dto.getNombres());
        usuario.setFechaNacimiento(dto.getFechaNacimiento());
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setCorreoElectronico(dto.getCorreoElectronico());
        usuario.setActivo(dto.isActivo());
        usuario.setContrasenia(passwordEncoder.encode(dto.getContrasenia()));
        usuario.setRoles(new HashSet<>()); // Se pueden asignar roles posteriormente
        return usuario;
    }
}

