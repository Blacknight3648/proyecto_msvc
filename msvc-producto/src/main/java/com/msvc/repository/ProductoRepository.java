package com.msvc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msvc.model.entity.ProductoModel;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoModel, Long> {
    Optional<ProductoModel> findByNombre(String nombre);

}
