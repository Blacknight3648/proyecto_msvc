package com.perfulandia.msvc.comprobante.venta.clients;

import com.perfulandia.msvc.comprobante.venta.models.Vendedor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-vendedor", url = "localhost:8023/api/v1/vendedor")
public interface VendedorClientRest {

    @GetMapping("{id}")
    Vendedor findById(@PathVariable Long id);
}
