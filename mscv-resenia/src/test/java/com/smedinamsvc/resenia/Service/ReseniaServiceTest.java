package com.smedinamsvc.resenia.Service;

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

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReseniaServiceTest {

    @Mock
    private ReseniaRepository reseniaRepository;

    @InjectMocks
    private ReseniaServiceImpl reseniaService;

    private Resenia reseniaTest;

    // Se ejecuta antes de cada test
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
    @DisplayName("Debe guardar una reseña")
    public void shouldSaveResenia() throws ReseniaExceptions {
        when(reseniaRepository.save(reseniaTest)).thenReturn(reseniaTest);

        Resenia result = reseniaService.save(reseniaTest);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(reseniaTest);

        verify(reseniaRepository, times(1)).save(reseniaTest);
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