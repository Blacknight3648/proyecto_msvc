package com.perfulandia.msvc.comprobante.venta.services;

import com.perfulandia.msvc.comprobante.venta.clients.CarritoClientRest;
import com.perfulandia.msvc.comprobante.venta.clients.ClienteClientRest;
import com.perfulandia.msvc.comprobante.venta.clients.SucursalClientRest;
import com.perfulandia.msvc.comprobante.venta.clients.VendedorClientRest;
import com.perfulandia.msvc.comprobante.venta.dtos.*;
import com.perfulandia.msvc.comprobante.venta.exceptions.ComprobanteException;
import com.perfulandia.msvc.comprobante.venta.models.Carrito;
import com.perfulandia.msvc.comprobante.venta.models.Cliente;
import com.perfulandia.msvc.comprobante.venta.models.Sucursal;
import com.perfulandia.msvc.comprobante.venta.models.Vendedor;
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

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ComprobanteServiceTests {

    @Mock
    private ClienteClientRest clienteClientRest;

    @Mock
    private VendedorClientRest vendedorClientRest;

    @Mock
    private SucursalClientRest sucursalClientRest;

    @Mock
    private CarritoClientRest carritoClientRest;

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


            int numero = faker.number().numberBetween(8000000, 25000000);
            String digito = faker.regexify("[0-9K]");
            String cuerpo = String.format("%d", numero).replace(',','.');
            String rut = cuerpo + "-" + digito;

            comprobante.setFactura(faker.bool().bool());
            comprobante.setHoraComprobante(LocalDateTime.now().minusDays(faker.number().numberBetween(0, 30)));


            comprobante.setIdCliente(faker.number().numberBetween(1L,100L));
            comprobante.setIdVendedor(faker.number().numberBetween(1L,100L));
            comprobante.setIdSucursal(1L);
            comprobante.setIdCarrito(faker.number().numberBetween(1L,100L));

            this.comprobantes.add(comprobante);
        }
    }

    @Test
    @DisplayName("Debe listar todos los comprobantes")
    public void shouldFindAllComprobantes(){


        when(comprobanteRepository.findAll()).thenReturn(this.comprobantes);


        List<ComprobanteDTO> result = comprobanteService.findAll();


        assertThat(result).hasSize(100);
        assertThat(result.getFirst().getSucursal()).isEqualTo(1);

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

    @Test
    @DisplayName("Debe entregar una excepcion cuando medico id no exista")
    public void shouldNotFindMedicoById(){
        Long idInexistente = 999L;
        when(comprobanteRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            comprobanteService.findById(idInexistente);
        }).isInstanceOf(ComprobanteException.class)
                .hasMessageContaining("El comprobante con id "+idInexistente+" no se encuentra en la base de datos");
        verify(comprobanteRepository,times(1)).save(any(Comprobante.class));
    }

    @Test
    @DisplayName("Debe guardar un nuevo medico")
    public void shouldSaveComprobante(){
        when(comprobanteRepository.save(any(Comprobante.class))).thenReturn(this.comprobantePrueba);
        Comprobante result = comprobanteService.save(this.comprobantePrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.comprobantePrueba);
        verify(comprobanteRepository,times(1)).save(any(Comprobante.class));
    }

}
