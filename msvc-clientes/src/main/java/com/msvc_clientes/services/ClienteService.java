package com.msvc_clientes.services;

import com.msvc_clientes.models.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> findAll();
    Cliente findById(Long id);
    Cliente save(Cliente cliente);
    Cliente delete(Cliente cliente);


}
