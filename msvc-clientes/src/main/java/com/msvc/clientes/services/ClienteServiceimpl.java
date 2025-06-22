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
    public ClienteDTO findByRunCliente(String runCliente){

        String run = runCliente.toUpperCase();

        Cliente cliente = clienteRepository.findByRunCliente(run).orElseThrow(()-> new ClienteException("El cliente con el rut "+ run + " no esta en la base de datos"));

    /*    try {
            this.clienteRepository.findByRunCliente(run);
        }catch (FeignException ex){
            throw new ClienteException("El cliente con el rut "+ run + " no esta en la base de datos");
        }
    */
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setRunCliente(cliente.getRunCliente());
        clienteDTO.setCorreoCliente(cliente.getCorreoCliente());
        clienteDTO.setNombreCompleto(cliente.getNombreCompleto());
        clienteDTO.setFechaNacimiento(cliente.getFechaNacimiento());
        clienteDTO.setEstadoCuenta(cliente.isEstadoCuenta());

        return clienteDTO;

    }


    @Override
    public Cliente save(Cliente cliente) {
        cliente.setEstadoCuenta(true);

        // this.clienteRepository.findByRun(cliente.getRunCliente()).orElse();

        if(!this.clienteRepository.findByRunCliente(cliente.getRunCliente()).isPresent()){
            return clienteRepository.save(cliente);
        }else{
            throw new RuntimeException();
        }


    }

    @Override
    public Cliente deleteById(Long id){

        if (!clienteRepository.existsById(id)){
            throw new ClienteException("No se puede eliminar: cliente no esta en sistema");
        }
        clienteRepository.deleteById(id);

        return null;
    }

    @Override
    public Cliente update(Long id, ClienteDTO clienteDTO) {

        Cliente cliente = this.clienteRepository.findById(id).orElseThrow(
                () -> new ClienteException("El cliente con id "+ id + " no se encuentra en la base de datos")
        );

        cliente.setFechaNacimiento(clienteDTO.getFechaNacimiento());
        cliente.setNombreCompleto(clienteDTO.getNombreCompleto());
        cliente.setCorreoCliente(clienteDTO.getCorreoCliente());

        Cliente update = clienteRepository.save(cliente);

        return  update;
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
