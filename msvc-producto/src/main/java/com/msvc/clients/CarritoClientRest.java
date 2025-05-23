package com.msvc.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.msvc.model.Carrito;

import java.util.List;

@FeignClient(name = "msvc-carrito", url = "http://localhost:8026/api/v1/carritos")
public interface CarritoClientRest {

    @GetMapping
    List<Carrito> findByIdProducto(@PathVariable Long id);
}
