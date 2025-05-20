package com.msvc.productos.services;

import com.msvc.productos.clients.CarritoClientRest;
import com.msvc.productos.clients.ComprobanteClientRest;
import com.msvc.productos.dtos.ProductoDTO;
import com.msvc.productos.model.Carrito;
import com.msvc.productos.model.entity.ProductoModel;
import com.msvc.productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CarritoClientRest carritoClientRest;

    @Autowired
    private ComprobanteClientRest comprobanteClientRest;

    @Override
    public List<ProductoModel> findAll() {
        return List.of();
    }

    @Override
    public ProductoModel findById(Long id) {
        return null;
    }

    @Override
    public ProductoModel save(ProductoModel productoModel) {
        return null;
    }

    @Override
    public List<ProductoModel> findByClienteId(Long clienteId) {
        return List.of();
    }

    @Override
    public List<ProductoModel> findByComprobanteId(Long comprobanteId) {
        return List.of();
    }



}
