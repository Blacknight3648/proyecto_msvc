package com.smedinamsvc.resenia.service.impl;

import com.smedinamsvc.resenia.model.Resenia;
import com.smedinamsvc.resenia.repository.ReseniaRepository;
import com.smedinamsvc.resenia.service.ReseniaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReseniaServiceImpl implements ReseniaService {

    @Autowired
    private ReseniaRepository reseniaRepository;

    @Override
    public List<Resenia> findAll() {
        return reseniaRepository.findAll();
    }

    @Override
    public List<Resenia> findByProductoId(Long productoId) {
        return reseniaRepository.findByProductoId(productoId);
    }

    @Override
    public Resenia findById(Long id) {
        return reseniaRepository.findByProductoId(id)
                .orElseThrow(() -> new ReseniaExceptions("Reseña no encontrada con ID: "));
    }

    @Override
    public Resenia save(Resenia resenia) {
        return reseniaRepository.save(resenia);
    }

    @Override
    public void deleteById(Long id) {
        if (!reseniaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Reseña no encontrada con ID: " + id);
        }
        reseniaRepository.deleteById(id);
    }

}
