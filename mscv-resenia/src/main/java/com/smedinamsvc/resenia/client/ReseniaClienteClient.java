package com.smedinamsvc.resenia.client;

import com.smedinamsvc.resenia.dtos.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "cliente-client", url = "http://localhost:8020/api/v1/clientes")
public interface ReseniaClienteClient {

    @GetMapping("/{id}")
    ClienteDTO getClienteById(@PathVariable("id") Long id);

}
