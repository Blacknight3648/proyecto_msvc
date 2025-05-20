package com.smedinamsvc.resenia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smedinamsvc.resenia.model.Resenia;

@Repository
public interface ReseniaRepository extends JpaRepository<Resenia, Long> {

    // Método para buscar todas las reseñas de un producto específico
    List<Resenia> findByProductoId(Long productoId);
}

///Siempre revisar el JPA para que sean compatibles los datos. Eso era lo que causaba problema en el Service impl