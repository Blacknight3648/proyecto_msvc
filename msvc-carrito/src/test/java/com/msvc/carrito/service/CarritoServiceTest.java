package com.msvc.carrito.service;


import com.msvc.carrito.clients.ClienteClientRest;
import com.msvc.carrito.clients.ProductoClientRest;
import com.msvc.carrito.clients.VendedorClientRest;
import com.msvc.carrito.model.Cliente;
import com.msvc.carrito.model.Producto;
import com.msvc.carrito.model.Vendedor;
import com.msvc.carrito.model.entity.Carrito;
import com.msvc.carrito.repositories.CarritoRespository;
import com.msvc.carrito.services.CarritoServiceImpl;
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
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarritoServiceTest {

    @Mock
    private ClienteClientRest clienteClientRest;

    @Mock
    private VendedorClientRest vendedorClientRest;

    @Mock
    private ProductoClientRest productoClientRest;

    @Mock
    private CarritoRespository carritoRespository;

    @InjectMocks
    private CarritoServiceImpl carritoService;
    private Cliente clienteTest;
    private Producto productoTest;
    private Vendedor vendedorTest;
    private Carrito carritoTest;
    private List<Carrito> carritos = new ArrayList<>();

    @BeforeEach
    public void setUp(){

        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);
        cliente.setRunCliente("22.107.273-1");
        cliente.setFechaNacimiento(LocalDate.now());
        cliente.setNombreCompleto("Said Torres");

        Vendedor vendedor = new Vendedor();
        vendedor.setIdVendedor(1L);
        vendedor.setRunVendedor("20.342.231-1");
        vendedor.setFechaNacimiento(LocalDate.now());
        vendedor.setNombreCompleto("Nicolas Figueroa");

        Carrito carrito = new Carrito();
        carrito.setIdCarrito(1L);
        carrito.setIdProducto(1L);
        carrito.setIdVendedor(1L);
        carrito.setIdCliente(1L);
        carrito.setPrecioTotal(9999);
        carrito.setCupon("Cupon");

        Producto producto = new Producto();
        producto.setIdProducto(1L);
        producto.setNombreProducto("Perfume 1");
        producto.setDescProducto("Fragancia de rosas");
        producto.setPrecio(1000);

        this.carritos.add(carrito);

        this.carritoTest = new Carrito(
                1L, 1L, 5, 9999, "cupon", 1L, 1L
        );
    }

    @Test
    @DisplayName("Debe listar todos los carritos")
    public void shouldFindAllCarritos(){

        this.carritos.add(this.carritoTest);

        when(carritoRespository.findAll()).thenReturn(this.carritos);

        List<Carrito> result = carritoService.findAllModels();

        verify(carritoRespository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe encontrar carrito por id")
    public void shouldFindCarritoById(){
        when(carritoRespository.findById(1L)).thenReturn(Optional.of(this.carritoTest));
        Carrito result = carritoService.findById(1L);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.carritoTest);
        verify(carritoRespository, times(1)).findById(1L);
    }
}
