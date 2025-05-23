package com.msvc.clientes.services;

import com.msvc.clientes.DTO.ClienteDTO;
import com.msvc.clientes.Exceptions.ClienteException;
import com.msvc.clientes.models.Cliente;
import com.msvc.clientes.repository.ClienteRepository;
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
    public Cliente suspend(Long id, ClienteDTO clienteDTO) {

        Cliente cliente = this.clienteRepository.findById(id).orElseThrow(
                () -> new ClienteException("El cliente con id " + id + " no se encuentra en la base de datos")
        );
        
        cliente.setEstadoCuenta(clienteDTO.isEstadoCuenta());

        Cliente update = clienteRepository.save(cliente);

        return update;
    }


}
