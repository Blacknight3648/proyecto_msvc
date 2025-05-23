package com.msvc.services;

import org.springframework.stereotype.Service;

import com.msvc.model.entity.ProductoModel;

import java.util.List;

@Service
public interface ProductoService {

    List<ProductoModel> findAll();
    ProductoModel findById(Long id);
    ProductoModel save(ProductoModel productoModel);

}
