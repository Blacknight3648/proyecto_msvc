package com.msvc.clientes.ServicesTest;


import com.msvc.clientes.DTO.ClienteDTO;
import com.msvc.clientes.Exceptions.ClienteException;
import com.msvc.clientes.models.Cliente;
import com.msvc.clientes.repository.ClienteRepository;
import com.msvc.clientes.services.ClienteServiceimpl;
import jakarta.validation.constraints.Null;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        Faker faker = new Faker(Locale.of("es", "cl"));
        for (int i=0;i<100;i++){
            Cliente cliente = new Cliente();
            cliente.setIdCliente((long)i);
            LocalDate fechaNacimiento = LocalDate.now().minusYears(faker.number().numberBetween(18, 80));
            cliente.setFechaNacimiento(fechaNacimiento);
            cliente.setNombreCompleto(faker.name().fullName());
            cliente.setCorreoCliente(faker.internet().emailAddress());
            cliente.setEstadoCuenta(faker.bool().bool());

            String numeroString = faker.idNumber().valid().replaceAll("-","");
            String ultimo = numeroString.substring(numeroString.length()-1);
            String restante = numeroString.substring(0,numeroString.length()-1);
            cliente.setRunCliente(restante+"-"+ultimo);

            this.clientes.add(cliente);
        }


        this.clientePrueba = new Cliente(
                1L, "11111111-1",(LocalDate.now()), "Fernando Alvira", "djdkjkso@gmail.com", true
        );


    }

    @Test
    @DisplayName("Debe listar todo los clientes")
    public void shouldFindAllClientes(){

        this.clientes.add(this.clientePrueba);

        when(clienteRepository.findAll()).thenReturn(this.clientes);

        List<Cliente> result = clienteServiceimpl.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(this.clientePrueba);

        verify(clienteRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("Debe buscar un cliente por id")
    public void shouldFindClientesById(){

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(this.clientePrueba));

        Cliente result = clienteServiceimpl.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.clientePrueba);

        verify(clienteRepository, times(1)).findById(1L);

    }



    @Test
    @DisplayName("No debe buscar un cliente por id")
    public void shouldNotFindClientesById(){
        Long idInexistente = 999L;
        when(clienteRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(() ->
            clienteServiceimpl.findById(idInexistente)).isInstanceOf(ClienteException.class).hasMessageContaining("El cliente con id "+ idInexistente + " no se encuentra en la base de datos");

        verify(clienteRepository, times(1)).findById(idInexistente);

    }

    @Test
    @DisplayName("Debe mostrar clientes por rut")
    public void shouldFindClientesByRunCliente(){


        when(clienteRepository.findByRunCliente("11111111-1")).thenReturn(Optional.of(this.clientePrueba));

        Cliente result = clienteServiceimpl.findByRunCliente("11111111-1");

        assertThat(result).isNotNull();
        assertThat(result.getRunCliente()).isEqualTo(clientePrueba.getRunCliente());
        assertThat(result.getCorreoCliente()).isEqualTo(clientePrueba.getCorreoCliente());
        assertThat(result.getNombreCompleto()).isEqualTo(clientePrueba.getNombreCompleto());
        assertThat(result.getFechaNacimiento()).isEqualTo(clientePrueba.getFechaNacimiento());
        assertThat(result.isEstadoCuenta()).isEqualTo(clientePrueba.isEstadoCuenta());

        verify(clienteRepository, times(1)).findByRunCliente("11111111-1");

    }

    @Test
    @DisplayName("Debe guardar clientes")
    public void shouldSaveClientes(){


        when(clienteRepository.save(any(Cliente.class))).thenReturn(this.clientePrueba);

        Cliente result = clienteServiceimpl.save(this.clientePrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.clientePrueba);

        verify(clienteRepository, times(1)).save(any(Cliente.class));

    }

    @Test
    @DisplayName("No debe guardar clientes")
    public void shouldNotSaveClientes(){

        when(clienteRepository.save(any(Cliente.class))).thenThrow(new ClienteException("Error al guardar cliente"));

        assertThatThrownBy(()-> clienteServiceimpl.save(this.clientePrueba)).isInstanceOf(ClienteException.class).hasMessageContaining("Error al guarda");

        verify(clienteRepository, times(1)).save(any(Cliente.class));

    }



}
