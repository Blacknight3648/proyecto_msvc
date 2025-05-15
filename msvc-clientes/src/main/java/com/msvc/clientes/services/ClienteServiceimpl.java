package com.msvc.clientes.services;

import com.msvc.clientes.Exceptions.ClienteException;
import com.msvc.clientes.models.Cliente;
import com.msvc.clientes.repository.ClienteRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceimpl implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll() {
        return this.clienteRepository.findAll();
    }

    @Override
    public Cliente findById(Long id){

        return this.clienteRepository.findById(id).orElseThrow(
                ()-> new ClienteException("El cliente con id "+ id + " no se encuentra en la base de datos")

        );
    }

    @Override
    public Cliente save(Cliente cliente) {
        cliente.setEstadoCuenta(true);
        return clienteRepository.save(cliente);

    }

    @Override
    public Cliente suspend(Cliente cliente) {
        return null;
    }


}
