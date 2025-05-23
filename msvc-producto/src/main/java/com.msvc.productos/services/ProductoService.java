package com.msvc.productos.services;

import com.msvc.productos.model.entity.ProductoModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductoService {

    List<ProductoModel> findAll();
    ProductoModel findById(Long id);
    ProductoModel save(ProductoModel productoModel);

}
