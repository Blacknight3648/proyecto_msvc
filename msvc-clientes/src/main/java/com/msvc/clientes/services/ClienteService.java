package com.msvc.clientes.services;

import com.msvc.clientes.DTO.ClienteDTO;
import com.msvc.clientes.models.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> findAll();
    Cliente findById(Long id);
    Cliente save(Cliente cliente);
    Cliente suspend(Long id, ClienteDTO clienteDTO);

}
