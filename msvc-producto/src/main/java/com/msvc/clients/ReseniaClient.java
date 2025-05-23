package com.msvc.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.msvc.dtos.ReseniaDTO;

import java.util.List;

@FeignClient(name = "resenia-client", url = "http://localhost:8021/api/v1/resenias")
public interface ReseniaClient {

    // Ejemplo: obtener todas las reseñas de un producto por su id
    @GetMapping("/producto/{id}")
    List<ReseniaDTO> getReseniasByProductoId(@PathVariable("id") Long id);

}

