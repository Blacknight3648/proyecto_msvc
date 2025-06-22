package com.msvc.clientes.services;

import com.msvc.clientes.DTO.ClienteDTO;
import com.msvc.clientes.models.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> findAll();
    Cliente findById(Long id);
    ClienteDTO findByRunCliente(String runCliente);
    Cliente save(Cliente cliente);
    Cliente deleteById(Long id);
    Cliente update(Long id, ClienteDTO clienteDTO);
    Cliente suspend(Long id, ClienteDTO clienteDTO);

}
