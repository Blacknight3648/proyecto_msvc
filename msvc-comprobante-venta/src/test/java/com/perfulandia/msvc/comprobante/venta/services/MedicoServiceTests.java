package com.perfulandia.msvc.comprobante.venta.services;

import com.perfulandia.msvc.comprobante.venta.dtos.ComprobanteDTO;
import com.perfulandia.msvc.comprobante.venta.models.entities.Comprobante;
import com.perfulandia.msvc.comprobante.venta.repositories.ComprobanteRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicoServiceTests {

    @Mock
    private ComprobanteRepository comprobanteRepository;

    @InjectMocks
    private ComprobanteServiceImpl comprobanteService;

    private Comprobante comprobantePrueba;
    private List<Comprobante> comprobantes = new ArrayList<>();

    @BeforeEach
    public void setUp(){

        Faker faker = new Faker(Locale.of("es", "CL"));
        for(int i=0;i<100;i++){
            Comprobante comprobante = new Comprobante();

            comprobante.setFactura(faker.bool().bool());
            comprobante.setHoraComprobante(LocalDateTime.now().minusDays(faker.number().numberBetween(0, 30)));

            comprobante.setIdCliente(faker.number().numberBetween(1L, 100L));
            comprobante.setIdVendedor(faker.number().numberBetween(1L, 100L));
            comprobante.setIdSucursal(faker.number().numberBetween(1L,100L));
            comprobante.setIdCarrito(faker.number().numberBetween(1L,100L));

            this.comprobantes.add(comprobante);
        }
        this.comprobantePrueba = new Comprobante(
                1L,LocalDateTime.now(),1L,1L,1L,1L,true
        );
    }

    @Test
    @DisplayName("Debe listar todos los comprobantes")
    public void shouldFindAllComprobantes(){

        this.comprobantes.add(this.comprobantePrueba);

        when(comprobanteRepository.findAll()).thenReturn(this.comprobantes);

        List<Comprobante> result = comprobanteService.findAllN();

        assertThat(result).hasSize(101);
        assertThat(result).contains(this.comprobantePrueba);

        verify(comprobanteRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe encontrar comprobante por id")
    public void shouldFindComprobanteById(){
        when(comprobanteRepository.findById(1L)).thenReturn(Optional.of(this.comprobantePrueba));
        Comprobante result = comprobanteService.findById(1L);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.comprobantePrueba);
        verify(comprobanteRepository,times(1)).findById(1L);
    }

}
