package com.msvc.clientes.ServicesTest;


import com.msvc.clientes.models.Cliente;
import com.msvc.clientes.repository.ClienteRepository;
import com.msvc.clientes.services.ClienteServiceimpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServicesTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceimpl clienteServiceimpl;

    private Cliente clientePrueba;
    private List<Cliente> clientes = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        this.clientePrueba=new Cliente();

    }

    @Test
    @DisplayName("Debe listar todo los medicos")
    public void shouldFindAllClientes(){

        this.clientes.add(this.clientePrueba);

        when(clienteRepository.findAll()).thenReturn(this.clientes);


        Cliente otroCliente = new Cliente();
        //Cliente otroCliente = new Cliente(1L,"22222111-3","2022-10-05","Cristopher", "frp@rr.cl", true);

        List<Cliente> result = clienteServiceimpl.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(this.clientePrueba);

        verify(clienteRepository, times(1)).findAll();

    }

}
