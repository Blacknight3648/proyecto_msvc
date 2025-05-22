package com.msvc.carrito.repositories;

import com.msvc.carrito.model.entity.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoRespository extends JpaRepository<Carrito, Long> {

    List<Carrito> findByIdCliente(Long idCliente);

    List<Carrito> findByIdVendedor(Long idVendedor);
}
