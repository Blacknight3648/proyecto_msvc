package com.perfulandia.msvc.comprobante.venta.clients;

import com.perfulandia.msvc.comprobante.venta.models.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-clientes", url = "localhost:8020/api/v1/clientes")
public interface ClienteClientRest {

    @GetMapping("/{id}")
    Cliente findById(@PathVariable Long id);
}
