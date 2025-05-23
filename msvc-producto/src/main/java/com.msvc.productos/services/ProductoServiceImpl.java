package com.msvc.productos.services;

import com.msvc.productos.clients.CarritoClientRest;
import com.msvc.productos.clients.ComprobanteClientRest;
import com.msvc.productos.dtos.ProductoDTO;
import com.msvc.productos.exceptions.ProductoException;
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

    @Override
    public List<ProductoModel> findAll() {
        return this.productoRepository.findAll();
    }

    @Override
    public ProductoModel findById(Long id) {
        return this.productoRepository.findById(id).orElseThrow(
                () -> new ProductoException("El producto con el id: " + id+"no se encuentra en la base de datos")
        );
    }

    @Override
    public ProductoModel save(ProductoModel productoModel) {
        if(this.productoRepository.findBynombre(productoModel.getNombreProducto()).isPresent()) {
            throw new ProductoException("El producto con el nombre: " + productoModel.getNombreProducto()
                    + " ya existe en la base de datos");
        }
        return this.productoRepository.save(productoModel);

    }
}
