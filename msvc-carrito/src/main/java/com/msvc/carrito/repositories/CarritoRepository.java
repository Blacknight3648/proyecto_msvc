package com.msvc.carrito.repositories;

import com.msvc.carrito.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarritoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByIdProducto(Long idProducto);

    List<Producto> findByIdVendedor(Long idVendedor);

}
