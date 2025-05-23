package com.msvc.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.msvc.model.ComprobanteVenta;

@FeignClient(name = "msvc-comprobantes", url = "http://localhost:8024/api/v1/comprobantes")
public interface ComprobanteClientRest {

    @GetMapping("/{id}")
    ComprobanteVenta findById(@PathVariable("id") Long id);
}
