package com.msvc.vendedor.ServicesTest;

import com.msvc.vendedor.exception.VendedorException;
import com.msvc.vendedor.models.Vendedor;
import com.msvc.vendedor.repositories.VendedorRepository;
import com.msvc.vendedor.services.VendedorServiceimpl;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VendedorServiceTest {

    @Mock
    private VendedorRepository vendedorRepository;

    @InjectMocks
    private VendedorServiceimpl vendedorServiceimpl;

    private Vendedor vendedorPrueba;

    private List<Vendedor> vendedores = new ArrayList<>();

    @BeforeEach
    public void setUp(){

        Faker faker = new Faker(Locale.of("es", "cl"));
        for(int i=0;i<100;i++){

            Vendedor vendedor = new Vendedor();
            vendedor.setIdVendedor((long)i);
            LocalDate fechaNacimiento = LocalDate.now().minusYears(faker.number().numberBetween(18, 80));
            vendedor.setFechaNacimiento(fechaNacimiento);
            vendedor.setNombreCompleto(faker.name().fullName());
            vendedor.setCorreoVendedor(faker.internet().emailAddress());
            vendedor.setEstadoCuenta(faker.bool().bool());

            String numeroString = faker.idNumber().valid().replaceAll("-", "");
            String ultimo = numeroString.substring(numeroString.length()-1);
            String restante = numeroString.substring(0, numeroString.length()-1);
            vendedor.setRunVendedor(restante+"-"+ultimo);

            this.vendedores.add(vendedor);

            this.vendedorPrueba = new Vendedor(1L, "22222222-1",(LocalDate.now()), "Fernando Alvira", "djdkjkso@gmail.com", true);

        }

    }

    @Test
    @DisplayName("Debe listar todos los clientes")
    public void shouldFindAllClientes(){

        this.vendedores.add(this.vendedorPrueba);

        when(vendedorRepository.findAll()).thenReturn(this.vendedores);

        List<Vendedor> result = vendedorServiceimpl.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(this.vendedorPrueba);

        verify(vendedorRepository, times(1)).findAll();


    }

    @Test
    @DisplayName("Debe buscar un vendedor por id")
    public void ShouldFindVendedoresById(){

        when(vendedorRepository.findById(1L)).thenReturn(Optional.of(this.vendedorPrueba));

        Vendedor result = vendedorServiceimpl.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.vendedorPrueba);

        verify(vendedorRepository,times(1)).findById(1L);


    }

    @Test
    @DisplayName("No debe buscar un vendedor por id")
    public void shouldNotFindVendedorById(){

        Long idInexistente = 999L;

        when(vendedorRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> vendedorServiceimpl.findById(idInexistente)).isInstanceOf(VendedorException.class).hasMessageContaining("El vendedor con id "+ idInexistente + " no se encuentra en la base de datos");

        verify(vendedorRepository,times(1)).findById(idInexistente);
    }





}
