package com.mscv.proveedores.service;

import java.util.List;

import com.mscv.proveedores.DTO.ProveedorDTO;
import com.mscv.proveedores.model.Proveedores;

public interface ProveedorService {

    List<Proveedores> findAll();
    Proveedores findById(Long id);
    Proveedores save(Proveedores proveedor);
    void deleteById(Long id);
    Proveedores suspend(Long id, ProveedorDTO proveedorDTO);
    Proveedores update(Long id, ProveedorDTO proveedorDTO);
}

//Arreglar un servicio por metod0