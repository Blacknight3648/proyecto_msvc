package com.smedinamsvc.resenia.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.smedinamsvc.resenia.dtos.ProductoDTO;

@FeignClient(name = "producto-client", url = "http://localhost:8022/api/v1/productos")
public interface ReseniaProductoClient {

    @GetMapping("/{id}")
    ProductoDTO getProductoById(@PathVariable("id") Integer id);

}