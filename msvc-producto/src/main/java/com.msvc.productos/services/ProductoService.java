package com.msvc.productos.services;

import com.msvc.productos.model.entity.ProductoModel;

import java.util.List;

public interface ProductoService {

    List<ProductoModel> findAll();
    ProductoModel findById(Long id);
    ProductoModel save(ProductoModel productoModel);
    List<ProductoModel> findByClienteId(Long clienteId);
    List<ProductoModel> findByComprobanteId (Long comprobanteId);

}
