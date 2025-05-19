package com.msvc.carrito.clients;

import com.msvc.carrito.model.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-producto", url = "localhost:8022/api/v1/producto")
public interface ProductoClientRest {

    @GetMapping("/{id}")
    Producto findById(@PathVariable Long id);

}
