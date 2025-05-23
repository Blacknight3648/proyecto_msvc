package com.smedinamsvc.resenia.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smedinamsvc.resenia.Exceptions.ReseniaExceptions;
import com.smedinamsvc.resenia.client.ReseniaProductoClient;
import com.smedinamsvc.resenia.dtos.ProductoDTO;
import com.smedinamsvc.resenia.model.Resenia;
import com.smedinamsvc.resenia.repository.ReseniaRepository;
import com.smedinamsvc.resenia.service.ReseniaService;

@Service
public class ReseniaServiceImpl implements ReseniaService {

    @Autowired
    private ReseniaRepository reseniaRepository;

    @Autowired
    private ReseniaProductoClient productoClient;

    @Override
    public List<Resenia> findAll() {
        return reseniaRepository.findAll();
    }

    @Override
    public List<Resenia> findByProductoId(Long productoId) {
        // Puedes usar el cliente Feign si necesitas validar que el producto exista
        ProductoDTO producto = productoClient.getProductoById(productoId);
        if (producto == null) {
            throw new ReseniaExceptions("Producto con ID " + productoId + " no encontrado.");
        }
        return reseniaRepository.findByProductoId(productoId);
    }

    @Override
    public Resenia findById(Long id) {
        Optional<Resenia> resenia = reseniaRepository.findById(id);
        return resenia.orElseThrow(() ->
                new ReseniaExceptions("Reseña con ID " + id + " no encontrada."));
    }

    @Override
    public Resenia save(Resenia resenia) {
        // Puedes validar si el producto asociado existe antes de guardar
        ProductoDTO producto = productoClient.getProductoById(resenia.getIdProducto());
        if (producto == null) {
            throw new ReseniaExceptions("No se puede guardar la reseña: producto no válido.");
        }
        return reseniaRepository.save(resenia);
    }

    @Override
    public void deleteById(Long id) {
        if (!reseniaRepository.existsById(id)) {
            throw new ReseniaExceptions("No se puede eliminar: reseña no encontrada con ID " + id);
        }
        reseniaRepository.deleteById(id);
    }
}

