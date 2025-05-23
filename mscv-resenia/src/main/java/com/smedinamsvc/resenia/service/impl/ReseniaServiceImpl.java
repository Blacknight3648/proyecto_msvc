package com.smedinamsvc.resenia.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.smedinamsvc.resenia.Exceptions.ReseniaExceptions;
import com.smedinamsvc.resenia.client.ReseniaProductoClient;
import com.smedinamsvc.resenia.dtos.ProductoDTO;
import com.smedinamsvc.resenia.model.Resenia;
import com.smedinamsvc.resenia.repository.ReseniaRepository;
import com.smedinamsvc.resenia.service.ReseniaService;

import lombok.Data;

@Service
@Data
public class ReseniaServiceImpl implements ReseniaService {

    private final ReseniaRepository reseniaRepository;
    private final ReseniaProductoClient productoClient;

    public ReseniaServiceImpl(ReseniaRepository reseniaRepository, ReseniaProductoClient productoClient) {
        this.reseniaRepository = reseniaRepository;
        this.productoClient = productoClient;
    }

    @Override
    public List<Resenia> findAll() {
        return reseniaRepository.findAll();
    }

    @Override
    public List<Resenia> findByProductoId(Long productoId) {
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
        ProductoDTO producto = productoClient.getProductoById(resenia.getProductoId());
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


