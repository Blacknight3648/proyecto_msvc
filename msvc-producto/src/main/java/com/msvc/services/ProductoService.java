package com.msvc.services;

import com.msvc.dtos.ProductoDTO;
import org.springframework.stereotype.Service;

import com.msvc.model.entity.ProductoModel;

import java.util.List;


public interface ProductoService {

    List<ProductoModel> findAll();
    ProductoModel findById(Long id);
    ProductoModel save(ProductoModel productoModel);
    ProductoModel deleteById(Long id);
    ProductoModel update(Long id, ProductoDTO productoDTO);

}
