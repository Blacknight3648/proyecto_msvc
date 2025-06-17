package com.perfulandia.msvc.comprobante.venta.services;

import com.perfulandia.msvc.comprobante.venta.dtos.*;
import com.perfulandia.msvc.comprobante.venta.exceptions.ComprobanteException;
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
public class MedicoServiceTests {

    @Mock
    private ComprobanteRepository comprobanteRepository;

    @InjectMocks
    private ComprobanteServiceImpl comprobanteService;

    private Comprobante comprobantePrueba;
    private List<ComprobanteDTO> comprobantes = new ArrayList<>();

    @BeforeEach
    public void setUp(){

        Faker faker = new Faker(Locale.of("es", "CL"));
        for(int i=0;i<100;i++){

            ComprobanteDTO comprobante = new ComprobanteDTO();
            CarritoDTO carrito = new CarritoDTO();
            ClienteDTO cliente = new ClienteDTO();
            SucursalDTO sucursal = new SucursalDTO();
            VendedorDTO vendedor = new VendedorDTO();

            carrito.setIdProducto(faker.number().numberBetween(1L,100L));
            carrito.setCantidad(faker.number().numberBetween(1,100));
            carrito.setPrecioTotal(faker.number().numberBetween(1,100));
            carrito.setCupon(faker.bothify("?#??-#?##"));

            int numero = faker.number().numberBetween(8000000, 25000000);
            String digito = faker.regexify("[0-9K]");
            String cuerpo = String.format("%.d", numero).replace(',','.');
            String rut = cuerpo + "-" + digito;
            cliente.setRunCliente(rut);
            cliente.setNombreCompleto(faker.name().fullName());
            cliente.setFechaNacimiento(LocalDate.now().minusYears(faker.number().numberBetween(18,70)));


            sucursal.setDireccionSucursal(faker.address().fullAddress());
            sucursal.setNombreSucursal(faker.address().buildingNumber());

            vendedor.setRunVendedor(rut);
            vendedor.setNombreCompleto(faker.name().fullName());
            vendedor.setFechaNacimiento(LocalDate.now().minusYears(faker.number().numberBetween(18,50)));

            comprobante.setFactura(faker.bool().bool());
            comprobante.setHoraComprobante(LocalDateTime.now().minusDays(faker.number().numberBetween(0, 30)));

            comprobante.setCliente(cliente);
            comprobante.setVendedor(vendedor);
            comprobante.setSucursal(sucursal);
            comprobante.setCarrito(carrito);

            this.comprobantes.add(comprobante);
        }
    }

    @Test
    @DisplayName("Debe listar todos los comprobantes")
    public void shouldFindAllComprobantes(){

        this.comprobantes.add(this.comprobantePrueba);

        when(comprobanteRepository.findAll()).thenReturn(this.comprobantes);

        List<ComprobanteDTO> result = comprobanteService.findAll();

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
