package com.msvc.carrito.services;

import com.msvc.carrito.dtos.CarritoDTO;
import com.msvc.carrito.model.entity.Carrito;

import java.util.List;

public interface CarritoService {

    List<CarritoDTO> findAll();
    Carrito findById(Long id);
    Carrito save(Carrito carrito);
    List<Carrito> findByClienteId(Long clienteId);
    List<Carrito> findByVendedorId(Long vendedorId);
    void deleteByid(Long id);
    Carrito update(Long id, CarritoDTO carritoDTO);
}
