package com.perfulandia.msvc.comprobante.venta.clients;

import com.perfulandia.msvc.comprobante.venta.models.Sucursal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-sucursal", url = "localhost:8025/api/v1/sucursales")
public interface SucursalClientRest {

    @GetMapping("/{id}")
    Sucursal findById(@PathVariable Long id);
}
