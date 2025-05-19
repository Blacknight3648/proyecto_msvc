package com.msvc.carrito.repositories;

import com.msvc.carrito.model.Producto;
import com.msvc.carrito.model.entity.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    List<Carrito> findByIdProducto(Long idProducto);

    List<Carrito> findByIdVendedor(Long idVendedor);

}
