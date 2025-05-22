package com.msvc.carrito.repositories;

import com.msvc.carrito.model.entity.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarritoRespository extends JpaRepository<Carrito, Long> {

    List<Carrito> findByIdCliente(Long idCliente);

    List<Carrito> findByIdVendedor(Long idVendedor);
}
