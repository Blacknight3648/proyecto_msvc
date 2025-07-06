package com.smedinamsvc.resenia.Service;

import com.smedinamsvc.resenia.client.ReseniaClienteClient;
import com.smedinamsvc.resenia.client.ReseniaProductoClient;
import com.smedinamsvc.resenia.dtos.ClienteDTO;
import com.smedinamsvc.resenia.dtos.ProductoDTO;
import com.smedinamsvc.resenia.exceptions.ReseniaExceptions;
import com.smedinamsvc.resenia.model.Resenia;
import com.smedinamsvc.resenia.repository.ReseniaRepository;
import com.smedinamsvc.resenia.service.impl.ReseniaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReseniaServiceTest {

    @Mock
    private ReseniaProductoClient reseniaProductoClient;

    @Mock
    private ReseniaClienteClient reseniaClienteClient;

    @Mock
    private ReseniaRepository reseniaRepository;

    @InjectMocks
    private ReseniaServiceImpl reseniaService;

    private Resenia reseniaTest;

    @BeforeEach
    public void setUp() {
        reseniaTest = new Resenia(
                1L,         // id
                10L,        // productoId
                5,          // calificación
                "Excelente producto, lo recomiendo", // comentario
                100L        // clienteId
        );
    }

    @Test
    @DisplayName("Debe guardar una reseña (validando producto y cliente)")
    public void shouldSaveResenia() throws ReseniaExceptions {
        ProductoDTO productoDTO = new ProductoDTO(10L, "Producto Test", 5000, "Desc");
        ClienteDTO clienteDTO = new ClienteDTO(
                100L,              // idCliente
                "11-1",            // runCliente
                LocalDate.of(1990, 1, 1),
                "Cliente Test",
                "cliente@test.com",
                true
        );

        when(reseniaProductoClient.getProductoById(10L)).thenReturn(productoDTO);
        when(reseniaClienteClient.getClienteById(100L)).thenReturn(clienteDTO);
        when(reseniaRepository.save(reseniaTest)).thenReturn(reseniaTest);

        Resenia result = reseniaService.save(reseniaTest);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(reseniaTest);

        verify(reseniaProductoClient, times(1)).getProductoById(10L);
        verify(reseniaClienteClient, times(1)).getClienteById(100L);
        verify(reseniaRepository, times(1)).save(reseniaTest);
    }

    @Test
    @DisplayName("Debe lanzar excepción si el producto no existe")
    public void shouldThrowWhenProductNotFound() {
        when(reseniaProductoClient.getProductoById(10L)).thenThrow(new RuntimeException("Producto no encontrado"));

        assertThatThrownBy(() -> reseniaService.save(reseniaTest))
                .isInstanceOf(ReseniaExceptions.class)
                .hasMessageContaining("El producto con ID 10 no existe");

        verify(reseniaProductoClient, times(1)).getProductoById(10L);
        verify(reseniaClienteClient, never()).getClienteById(any());
        verify(reseniaRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe lanzar excepción si el cliente no existe")
    public void shouldThrowWhenClientNotFound() {
        ProductoDTO productoDTO = new ProductoDTO(10L, "Producto Test", 5000, "Desc");

        when(reseniaProductoClient.getProductoById(10L)).thenReturn(productoDTO);
        when(reseniaClienteClient.getClienteById(100L)).thenThrow(new RuntimeException("Cliente no encontrado"));

        assertThatThrownBy(() -> reseniaService.save(reseniaTest))
                .isInstanceOf(ReseniaExceptions.class)
                .hasMessageContaining("El cliente con ID 100 no existe");

        verify(reseniaProductoClient, times(1)).getProductoById(10L);
        verify(reseniaClienteClient, times(1)).getClienteById(100L);
        verify(reseniaRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe lanzar excepción al guardar reseña nula")
    public void shouldThrowWhenSavingNullResenia() {
        assertThatThrownBy(() -> reseniaService.save(null))
                .isInstanceOf(ReseniaExceptions.class)
                .hasMessageContaining("La reseña no puede ser nula");

        verify(reseniaRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe buscar una reseña por ID")
    public void shouldFindReseniaById() throws ReseniaExceptions {
        when(reseniaRepository.findById(1L)).thenReturn(Optional.of(reseniaTest));

        Resenia result = reseniaService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(reseniaTest);

        verify(reseniaRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debe lanzar excepción si no encuentra reseña por ID")
    public void shouldThrowWhenReseniaNotFound() {
        when(reseniaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> reseniaService.findById(99L))
                .isInstanceOf(ReseniaExceptions.class)
                .hasMessageContaining("No se encontró la reseña con ID: 99");

        verify(reseniaRepository, times(1)).findById(99L);
    }

    @Test
    @DisplayName("Debe listar todas las reseñas")
    public void shouldFindAllResenias() {
        when(reseniaRepository.findAll()).thenReturn(List.of(reseniaTest));

        List<Resenia> result = reseniaService.findAll();

        assertThat(result).isNotEmpty();
        assertThat(result).contains(reseniaTest);

        verify(reseniaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe listar reseñas por productoId")
    public void shouldFindReseniasByProductoId() throws ReseniaExceptions {
        when(reseniaRepository.findByProductoId(10L)).thenReturn(List.of(reseniaTest));

        List<Resenia> result = reseniaService.findByProductoId(10L);

        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getProductoId()).isEqualTo(10L);

        verify(reseniaRepository, times(1)).findByProductoId(10L);
    }

    @Test
    @DisplayName("Debe lanzar excepción si no hay reseñas para productoId")
    public void shouldThrowWhenNoReseniasForProducto() {
        when(reseniaRepository.findByProductoId(999L)).thenReturn(List.of());

        assertThatThrownBy(() -> reseniaService.findByProductoId(999L))
                .isInstanceOf(ReseniaExceptions.class)
                .hasMessageContaining("No se encontraron reseñas para el producto con ID: 999");

        verify(reseniaRepository, times(1)).findByProductoId(999L);
    }

    @Test
    @DisplayName("Debe actualizar reseña existente")
    public void shouldUpdateResenia() throws ReseniaExceptions {
        when(reseniaRepository.existsById(1L)).thenReturn(true);
        when(reseniaRepository.save(reseniaTest)).thenReturn(reseniaTest);

        Resenia result = reseniaService.update(reseniaTest);

        assertThat(result).isEqualTo(reseniaTest);

        verify(reseniaRepository, times(1)).existsById(1L);
        verify(reseniaRepository, times(1)).save(reseniaTest);
    }

    @Test
    @DisplayName("Debe lanzar excepción al actualizar reseña inexistente")
    public void shouldThrowWhenUpdatingNonExistentResenia() {
        when(reseniaRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> reseniaService.update(reseniaTest))
                .isInstanceOf(ReseniaExceptions.class)
                .hasMessageContaining("La reseña que intentas actualizar no existe");

        verify(reseniaRepository, times(1)).existsById(1L);
        verify(reseniaRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe eliminar una reseña por ID")
    public void shouldDeleteReseniaById() {
        reseniaService.deleteById(1L);
        verify(reseniaRepository, times(1)).deleteById(1L);
    }
}
