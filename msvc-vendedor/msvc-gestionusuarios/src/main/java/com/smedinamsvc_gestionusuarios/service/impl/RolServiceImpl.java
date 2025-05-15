package com.smedinamsvc_gestionusuarios.service.impl;

import com.smedinamsvc_gestionusuarios.model.Rol;
import com.smedinamsvc_gestionusuarios.repository.RolRepository;
import com.smedinamsvc_gestionusuarios.service.RolService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public List<Rol> obtenerTodosLosRoles() {
        return rolRepository.findAll();
    }

    @Override
    public Optional<Rol> obtenerRolPorId(Long id) {
        return rolRepository.findById(id);
    }

    @Override
    public Rol crearRol(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public Rol actualizarRol(Long id, Rol rol) {
        if (rolRepository.existsById(id)) {
            rol.setId(id);
            return rolRepository.save(rol);
        } else {
            throw new RuntimeException("Rol no encontrado");
        }
    }

    @Override
    public void eliminarRol(Long id) {
        if (rolRepository.existsById(id)) {
            rolRepository.deleteById(id);
        } else {
            throw new RuntimeException("Rol no encontrado");
        }
    }
}
