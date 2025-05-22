package com.msvc.productos.clients;

import com.msvc.productos.model.ComprobanteVenta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-comprobantes", url = "localhost:8024/api/v1/comprobantes")
public interface ComprobanteClientRest {

    @GetMapping("/{id}")
    ComprobanteVenta findById(@PathVariable Long id);
}
