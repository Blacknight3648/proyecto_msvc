package com.perfulandia.msvc.sucursal.services;

import com.perfulandia.msvc.sucursal.models.Sucursal;

import java.util.List;

public interface SucursalService {
    List<Sucursal> findAll();
    Sucursal findById(Long id);
    Sucursal save(Sucursal sucursal);
}
