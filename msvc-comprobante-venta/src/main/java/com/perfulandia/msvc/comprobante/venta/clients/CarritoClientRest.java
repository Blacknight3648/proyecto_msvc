package com.perfulandia.msvc.comprobante.venta.clients;

import com.perfulandia.msvc.comprobante.venta.models.Carrito;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-carrito",url = "localhost:8026/api/v1/carritos")
public interface CarritoClientRest {

    @GetMapping("/{id}")
    Carrito findById(@PathVariable Long id);
}
