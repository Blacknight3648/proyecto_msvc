package com.smedinamsvc.resenia.service.impl;

import com.smedinamsvc.resenia.exceptions.ReseniaExceptions;
import com.smedinamsvc.resenia.model.Resenia;
import com.smedinamsvc.resenia.repository.ReseniaRepository;
import com.smedinamsvc.resenia.service.ReseniaService;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
@Builder
public class ReseniaServiceImpl implements ReseniaService {

    @Autowired
    private ReseniaRepository reseniaRepository;


    @Override
    public List<Resenia> findAll() {
        return reseniaRepository.findAll();
    }


    //READ

    @Override
    public Resenia findById(Long id) throws ReseniaExceptions {
        return reseniaRepository.findById(id)
                .orElseThrow(() -> new ReseniaExceptions("No se encontró la reseña con ID: " + id));
    }

    @Override
    public List<Resenia> findByProductoId(Long productoId) throws ReseniaExceptions {
        List<Resenia> resenias = reseniaRepository.findByProductoId(productoId);
        if (resenias.isEmpty()) {
            throw new ReseniaExceptions("No se encontraron reseñas para el producto con ID: " + productoId);
        }
        return resenias;
    }

    //CREATE

    @Override
    public Resenia save(Resenia resenia) throws ReseniaExceptions {
        if (resenia == null) {
            throw new ReseniaExceptions("La reseña no puede ser nula");
        }
        return reseniaRepository.save(resenia);
    }

    //DELETE

    @Override
    public void deleteById(Long id) {
        reseniaRepository.deleteById(id);
    }

    //UPDATE

    @Override
    public Resenia update(Resenia resenia) throws ReseniaExceptions {
        if (resenia.getId() == null || !reseniaRepository.existsById(resenia.getId())) {
            throw new ReseniaExceptions("La reseña que intentas actualizar no existe");
        }
        return reseniaRepository.save(resenia);
    }

}


