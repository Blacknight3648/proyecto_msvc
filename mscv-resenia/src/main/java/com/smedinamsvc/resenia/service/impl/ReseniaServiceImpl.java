package com.smedinamsvc.resenia.service.impl;

import com.smedinamsvc.resenia.client.ReseniaClienteClient;
import com.smedinamsvc.resenia.client.ReseniaProductoClient;
import com.smedinamsvc.resenia.exceptions.ReseniaExceptions;
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

    @Autowired
    private ReseniaProductoClient productoClient;

    @Autowired
    private ReseniaClienteClient clienteClient;

    @Override
    public List<Resenia> findAll() {
        return reseniaRepository.findAll();
    }

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

    @Override
    public Resenia save(Resenia resenia) throws ReseniaExceptions {
        if (resenia == null) {
            throw new ReseniaExceptions("La reseña no puede ser nula");
        }

        // Validar existencia del producto
        try {
            productoClient.getProductoById(resenia.getProductoId());
        } catch (Exception e) {
            throw new ReseniaExceptions("El producto con ID " + resenia.getProductoId() + " no existe");
        }

        // Validar existencia del cliente
        try {
            clienteClient.getClienteById(resenia.getIdCliente());
        } catch (Exception e) {
            throw new ReseniaExceptions("El cliente con ID " + resenia.getIdCliente() + " no existe");
        }

        return reseniaRepository.save(resenia);
    }

    @Override
    public Resenia update(Resenia resenia) throws ReseniaExceptions {
        if (resenia.getId() == null || !reseniaRepository.existsById(resenia.getId())) {
            throw new ReseniaExceptions("La reseña que intentas actualizar no existe");
        }
        return reseniaRepository.save(resenia);
    }

    @Override
    public void deleteById(Long id) {
        reseniaRepository.deleteById(id);
    }
}


