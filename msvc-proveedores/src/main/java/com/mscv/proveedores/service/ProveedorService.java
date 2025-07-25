package com.mscv.proveedores.service;

import java.util.List;

import com.mscv.proveedores.Exceptions.ProveedorException;
import com.mscv.proveedores.model.Proveedor;

public interface ProveedorService {

    // READ: listar todos los proveedores
    List<Proveedor> findAll();

    // READ: buscar proveedor por ID
    Proveedor findById(Long id) throws ProveedorException;

    // READ: listar proveedores según estado de suspensión
    List<Proveedor> findBySuspencion(boolean suspencion);

    // CREATE: guardar un proveedor nuevo
    Proveedor save(Proveedor proveedor) throws ProveedorException;

    // DELETE: eliminar proveedor por ID
    void deleteById(Long id) throws ProveedorException;

    // UPDATE: actualizar proveedor completo (entidad Proveedores)
    Proveedor update(Proveedor proveedor) throws ProveedorException;
}
