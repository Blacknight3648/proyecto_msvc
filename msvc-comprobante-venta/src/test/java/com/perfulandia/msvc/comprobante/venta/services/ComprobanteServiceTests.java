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
    private Cliente clienteTest;
    private Vendedor vendedorTest;
    private Sucursal sucursalTest;
    private Carrito carritoTest;
    private Comprobante comprobanteTest;
    private List<Comprobante> comprobantes = new ArrayList<>();

    @BeforeEach
    public void setUp(){

        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);
        cliente.setRunCliente("21.509.150-3");
        cliente.setFechaNacimiento(LocalDate.now());
        cliente.setNombreCompleto("Ignacio Perez");

        Vendedor vendedor = new Vendedor();
        vendedor.setIdVendedor(1L);
        vendedor.setRunVendedor("21.509.150-3");
        vendedor.setFechaNacimiento(LocalDate.now());
        vendedor.setNombreCompleto("Ignacio Perez");

        Sucursal sucursal = new Sucursal();
        sucursal.setIdSucursal(1L);
        sucursal.setNombreSucursal("Valparaiso");
        sucursal.setDireccionSucursal("Av. Valparaiso");

        Carrito carrito = new Carrito();
        carrito.setIdCarrito(1L);
        carrito.setIdVendedor(1L);
        carrito.setIdCliente(1L);
        carrito.setPrecioTotal(9999);
        carrito.setCupon("Cupon");

        Comprobante comprobante = new Comprobante();
        comprobante.setIdComprobante(1L);
        comprobante.setHoraComprobante(LocalDateTime.now());
        comprobante.setFactura(true);
        comprobante.setIdVendedor(1L);
        comprobante.setIdCarrito(1L);
        comprobante.setIdSucursal(1L);
        comprobante.setIdCliente(1L);

        this.comprobantes.add(comprobante);

        this.comprobanteTest = new Comprobante(
          1L,LocalDateTime.now(),1L,1L,1L,1L,true
        );

    }

    @Test
    @DisplayName("Debe listar todos los comprobantes")
    public void shouldFindAllComprobantes(){

        this.comprobantes.add(this.comprobanteTest);

        when(comprobanteRepository.findAll()).thenReturn(this.comprobantes);

        List<Comprobante> result = comprobanteService.findAllModels();



        verify(comprobanteRepository, times(1)).findAll();


    }

    @Test
    @DisplayName("Debe encontrar comprobante por id")
    public void shouldFindComprobanteById(){
        when(comprobanteRepository.findById(1L)).thenReturn(Optional.of(this.comprobanteTest));
        Comprobante result = comprobanteService.findById(1L);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.comprobanteTest);
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
                .hasMessageContaining("El comprobante con id: "+idInexistente+" no se encuentra en la base de datos.");
        verify(comprobanteRepository,times(1)).save(any(Comprobante.class));
    }

    @Test
    @DisplayName("Debe guardar un nuevo medico")
    public void shouldSaveComprobante(){

        when(clienteClientRest.findById(1L)).thenReturn(this.clienteTest);
        when(vendedorClientRest.findById(1L)).thenReturn(this.vendedorTest);
        when(sucursalClientRest.findById(1L)).thenReturn(this.sucursalTest);
        when(comprobanteRepository.save(any(Comprobante.class))).thenReturn(this.comprobanteTest);

        Comprobante result = comprobanteService.save(this.comprobanteTest);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.comprobanteTest);

        verify(clienteClientRest, times(1)).findById(1L);
        verify(vendedorClientRest, times(1)).findById(1L);
        verify(sucursalClientRest, times(1)).findById(1L);

    }

    @Test
    @DisplayName("No debe guardar comprobantes")
    public void shouldNotSaveComprobantes(){

        when(comprobanteRepository.save(any(Comprobante.class))).thenThrow(new ComprobanteException("Error al validar vendedor, cliente o sucursal"));


        assertThatThrownBy(()-> comprobanteService.save(this.comprobanteTest)).isInstanceOf(Comprobante.class).hasMessageContaining("Error al guardar");


        verify(comprobanteService, times(1)).save(any(Comprobante.class));

    }
}




