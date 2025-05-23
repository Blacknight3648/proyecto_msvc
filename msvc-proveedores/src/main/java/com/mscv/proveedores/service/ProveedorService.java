package com.mscv.proveedores.service;

import java.util.List;

import com.mscv.proveedores.DTO.ProveedorDTO;
import com.mscv.proveedores.model.Proveedores;

public interface ProveedorService {

    List<Proveedores> findAll();
    Proveedores findById(Long id);
    ProveedorDTO save(ProveedorDTO proveedordDto);
    Proveedores suspend(Long id, ProveedorDTO proveedorDTO); // ejemplo si quieres suspender o eliminar lógicamente
    Proveedores save(Proveedores proveedor);
     
}
