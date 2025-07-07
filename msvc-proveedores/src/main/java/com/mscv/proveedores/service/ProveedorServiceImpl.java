package com.mscv.proveedores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mscv.proveedores.Exceptions.ProveedorException;
import com.mscv.proveedores.Repository.ProveedorRepository;
import com.mscv.proveedores.model.Proveedor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    // READ: listar todos
    @Override
    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    // READ: buscar por ID
    @Override
    public Proveedor findById(Long id) throws ProveedorException {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new ProveedorException("No se encontró el proveedor con ID: " + id));
    }

    // CREATE: guardar nuevo proveedor
    @Override
    public Proveedor save(Proveedor proveedor) throws ProveedorException {
        if (proveedor == null) {
            throw new ProveedorException("El proveedor no puede ser nulo");
        }
        return proveedorRepository.save(proveedor);
    }

    // DELETE: eliminar por ID
    @Override
    public void deleteById(Long id) throws ProveedorException {
        if (!proveedorRepository.existsById(id)) {
            throw new ProveedorException("No se puede eliminar: proveedor con ID " + id + " no existe");
        }
        proveedorRepository.deleteById(id);
    }

    // UPDATE: actualizar proveedor completo (razón social y RUN)
    @Override
    public Proveedor update(Proveedor proveedor) throws ProveedorException {
        if (proveedor.getIdProveedor() == null || !proveedorRepository.existsById(proveedor.getIdProveedor())) {
            throw new ProveedorException("El proveedor a actualizar no existe");
        }
        return proveedorRepository.save(proveedor);
    }

    // READ: listar por estado de suspensión
    @Override
    public List<Proveedor> findBySuspencion(boolean suspencion) {
        return proveedorRepository.findBySuspencion(suspencion);
    }


}
