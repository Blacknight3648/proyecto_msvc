package com.mscv.proveedores.service;

import java.util.List;

import com.mscv.proveedores.Exceptions.ProveedorException;
import com.mscv.proveedores.model.Proveedores;

public interface ProveedorService {

    // READ: listar todos los proveedores
    List<Proveedores> findAll();

    // READ: buscar proveedor por ID
    Proveedores findById(Long id) throws ProveedorException;

    // READ: listar proveedores según estado de suspensión
    List<Proveedores> findBySuspencion(boolean suspencion);

    // CREATE: guardar un proveedor nuevo
    Proveedores save(Proveedores proveedor) throws ProveedorException;

    // DELETE: eliminar proveedor por ID
    void deleteById(Long id) throws ProveedorException;

    // UPDATE: actualizar proveedor completo (entidad Proveedores)
    Proveedores update(Proveedores proveedor) throws ProveedorException;
}
