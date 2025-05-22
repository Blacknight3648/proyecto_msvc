package com.smedina_msvcproveedor.service;

import com.smedina_msvcproveedor.dto.ProveedorDTO;
import com.smedina_msvcproveedor.exception.ProveedorException;
import com.smedina_msvcproveedor.model.Proveedor;
import com.smedina_msvcproveedor.repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository repository;

    @Override
    public List<Proveedor> findAll() {
        return repository.findAll();
    }

    @Override
    public Proveedor findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProveedorException("Proveedor no encontrado con ID: " + id));
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        return repository.save(proveedor);
    }

    @Override
    public Proveedor suspend(Proveedor proveedor) {
        // Aquí podrías implementar lógica de suspensión si se define una columna de estado
        return repository.save(proveedor);
    }
}

