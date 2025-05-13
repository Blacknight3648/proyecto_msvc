package com.msvc.productos.repository;

import com.msvc.productos.model.entity.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoModel, Long> {
}
