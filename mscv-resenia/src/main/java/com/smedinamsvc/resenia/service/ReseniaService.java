package com.smedinamsvc.resenia.service;

import java.util.List;

import com.smedinamsvc.resenia.exceptions.ReseniaExceptions;
import com.smedinamsvc.resenia.model.Resenia;

public interface ReseniaService {

    List<Resenia> findAll();                                                        // Listar todas las reseñas

    List<Resenia> findByProductoId(Long productoId) throws ReseniaExceptions;       // Listar reseñas por producto

    Resenia findById(Long id) throws ReseniaExceptions;                             // Buscar reseña por id

    Resenia save(Resenia resenia) throws ReseniaExceptions;                         // Guardar reseña

    void deleteById(Long id);                                                       // Eliminar reseña por id

    Resenia update(Resenia resenia) throws ReseniaExceptions;

    Resenia updateById(Resenia resenia);
}
