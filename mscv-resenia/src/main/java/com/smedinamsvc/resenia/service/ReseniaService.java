package com.smedinamsvc.resenia.service;

import java.util.List;
import com.smedinamsvc.resenia.model.Resenia;

public interface ReseniaService {

    List<Resenia> findAll();                              // Listar todas las reseñas

    List<Resenia> findByProductoId(Long productoId);     // Listar reseñas por producto

    Resenia findById(Long id);                            // Buscar reseña por id

    Resenia save(Resenia resenia);                        // Guardar reseña

    void deleteById(Long id);                             // Eliminar reseña por id

    void updatebyId (Long id);

    //Actualizar el cuerpo de la resenia
    Resenia updateById(Resenia resenia);
}
