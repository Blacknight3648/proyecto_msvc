package com.smedinamsvc.resenia.service;

import java.util.List;

import com.smedinamsvc.resenia.model.Resenia;

public interface ReseniaService {

    List<Resenia> findAll();                              // Lista todas las reseñas
    List<Resenia> findByProductoId(Integer productoId);   // Lista todas las reseñas de un producto
    Resenia findById(Integer id);                         // Busca reseña por su id
    Resenia save(Resenia resenia);                        // Guarda una reseña completa (objeto)
    void deleteById(Integer id);                          // Elimina reseña por id (void)

}