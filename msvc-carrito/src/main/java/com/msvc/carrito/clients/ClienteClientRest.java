package com.msvc.carrito.clients;

import com.msvc.carrito.model.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-clientes", url = "localhost:8020/api/v1/clientes")
public interface ClienteClientRest {


    @GetMapping("/{id}")
    Cliente findById(@PathVariable Long id);
}
