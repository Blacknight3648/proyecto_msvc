package com.msvc.productos.services;


import com.msvc.exceptions.ProductoException;
import com.msvc.model.entity.ProductoModel;
import com.msvc.repository.ProductoRepository;
import com.msvc.services.ProductoService;
import com.msvc.services.ProductoServiceImpl;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {


    @Mock
    private ProductoRepository productoRepository;


    @InjectMocks
    private ProductoServiceImpl productoService;

    private ProductoModel productoPrueba;

    private List<ProductoModel> productoModel = new ArrayList<>();

    @BeforeEach
    public void setUp(){

        Faker faker = new Faker(Locale.of("es","CL"));
        for(int i=0;i<100;i++){

            ProductoModel productoModel = new ProductoModel();
            productoModel.setIdProducto(Long.valueOf(i));
            productoModel.setDescProducto(faker.lorem().paragraph());
            productoModel.setNombre(faker.name().fullName());
            productoModel.setPrecio(faker.number().numberBetween(10000,30000));

            this.productoModel.add(productoModel);
        }
        this.productoPrueba = new ProductoModel(
                    1L, "|  |", 15000, "Perfume 1"
        );

    }

    @Test
    @DisplayName("Debe listar todos los productos")
    public void shouldFindAllProductos(){

        this.productoModel.add(this.productoPrueba);

        when(productoRepository.findAll()).thenReturn(this.productoModel);

        List<ProductoModel> result = productoService.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(this.productoPrueba);

        verify(productoRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("Debe encontrar un producto por id")
    public void shouldFindProductoById(){
        when(productoRepository.findById(1L)).thenReturn(Optional.of(this.productoPrueba));
        ProductoModel result = productoService.findById(1L);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.productoPrueba);
        verify(productoRepository,times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debe entregar una excepcion cuando producto id no exista")
    public void shouldNotFindProductoById(){
        Long idInexistente = 999L;
        when(productoRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> {
            productoService.findById(idInexistente);
        }).isInstanceOf(ProductoException.class)
                .hasMessageContaining("El producto con el id: " + idInexistente + " no se encuentra en la base de datos");
        verify(productoRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar un nuevo medico")
    public void shouldSaveProducto(){
        when(productoRepository.save(any(ProductoModel.class))).thenReturn(this.productoPrueba);
        ProductoModel result = productoService.save(this.productoPrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.productoPrueba);
        verify(productoRepository, times(1)).save(any(ProductoModel.class));
    }
}
