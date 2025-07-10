package com.msvc.services;

import com.msvc.dtos.ProductoDTO;
import com.msvc.exceptions.ProductoException;
import com.msvc.model.entity.ProductoModel;
import com.msvc.repository.ProductoRepository;
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
            () -> new ProductoException("El producto con el id: " + id + " no se encuentra en la base de datos")
        );
    }

    @Override
    public ProductoModel save(ProductoModel productoModel) {
        if(this.productoRepository.findByNombre(productoModel.getNombre()).isPresent()) {
            throw new ProductoException("El producto con el nombre: " + productoModel.getNombre()
                    + " ya existe en la base de datos");
        }
        return this.productoRepository.save(productoModel);
    }

    @Override
    public ProductoModel deleteById(Long id) {
        if (!productoRepository.existsById(id)){
            throw new ProductoException("No se puede eliminar, producto no esta en sistema");
        }

        return null;
    }

    @Override
    public ProductoModel update(Long id, ProductoDTO productoDTO) {
        ProductoModel productoModel = this.productoRepository.findById(id).orElseThrow(() -> new ProductoException("El producto con id "+ id +" no se encuentra en la base de datos"));

        productoModel.setNombre(productoDTO.getDescProducto());
        productoModel.setDescProducto(productoDTO.getDescProducto());
        productoModel.setPrecio(productoDTO.getPrecio());

        ProductoModel update = productoRepository.save(productoModel);

        return update;
    }

}
