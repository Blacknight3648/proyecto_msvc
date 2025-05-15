package com.msvc.productos.repository;

import com.msvc.productos.dtos.ProductoDTO;
import com.msvc.productos.model.entity.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoModel, Long> {

    List<ProductoModel> findByIdCliente(Long idCliente);

    List<ProductoModel> findByIdComprobante(Long idComprobante);

}
