package com.mscv.proveedores.Service;

import com.mscv.proveedores.Exceptions.ProveedorException;
import com.mscv.proveedores.Repository.ProveedorRepository;
import com.mscv.proveedores.model.Proveedor;
import com.mscv.proveedores.service.ProveedorServiceImpl;
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
class ProveedorServiceTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorServiceImpl proveedorService;

    private Proveedor proveedorTest;

    @BeforeEach
    void setUp() {
        proveedorTest = new Proveedor();
        proveedorTest.setIdProveedor(1L);
        proveedorTest.setRunProveedor("12345678-9");
        proveedorTest.setRazonSocial("Ejemplo Spa");
        proveedorTest.setSuspencion(false);
    }

    @Test
    @DisplayName("Debe guardar un proveedor")
    void shouldSaveProveedor() throws ProveedorException {
        when(proveedorRepository.save(proveedorTest)).thenReturn(proveedorTest);

        Proveedor result = proveedorService.save(proveedorTest);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(proveedorTest);

        verify(proveedorRepository, times(1)).save(proveedorTest);
    }

    @Test
    @DisplayName("Debe lanzar excepción al guardar proveedor nulo")
    void shouldThrowWhenSavingNullProveedor() {
        assertThatThrownBy(() -> proveedorService.save(null))
                .isInstanceOf(ProveedorException.class)
                .hasMessageContaining("El proveedor no puede ser nulo");

        verify(proveedorRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe buscar proveedor por ID")
    void shouldFindProveedorById() throws ProveedorException {
        when(proveedorRepository.findById(1L)).thenReturn(Optional.of(proveedorTest));

        Proveedor result = proveedorService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getIdProveedor()).isEqualTo(1L);

        verify(proveedorRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debe lanzar excepción si proveedor no existe")
    void shouldThrowWhenProveedorNotFound() {
        when(proveedorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> proveedorService.findById(99L))
                .isInstanceOf(ProveedorException.class)
                .hasMessageContaining("No se encontró el proveedor con ID: 99");

        verify(proveedorRepository, times(1)).findById(99L);
    }

    @Test
    @DisplayName("Debe listar todos los proveedores")
    void shouldFindAllProveedores() {
        when(proveedorRepository.findAll()).thenReturn(List.of(proveedorTest));

        List<Proveedor> result = proveedorService.findAll();

        assertThat(result).isNotEmpty();
        assertThat(result).contains(proveedorTest);

        verify(proveedorRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe actualizar proveedor existente")
    void shouldUpdateProveedor() throws ProveedorException {
        when(proveedorRepository.existsById(1L)).thenReturn(true);
        when(proveedorRepository.save(proveedorTest)).thenReturn(proveedorTest);

        Proveedor result = proveedorService.update(proveedorTest);

        assertThat(result).isEqualTo(proveedorTest);

        verify(proveedorRepository, times(1)).existsById(1L);
        verify(proveedorRepository, times(1)).save(proveedorTest);
    }

    @Test
    @DisplayName("Debe lanzar excepción al actualizar proveedor inexistente")
    void shouldThrowWhenUpdatingNonExistentProveedor() {
        when(proveedorRepository.existsById(1L)).thenReturn(false);

        assertThatThrownBy(() -> proveedorService.update(proveedorTest))
                .isInstanceOf(ProveedorException.class)
                .hasMessageContaining("El proveedor a actualizar no existe");

        verify(proveedorRepository, times(1)).existsById(1L);
        verify(proveedorRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe eliminar proveedor por ID")
    void shouldDeleteProveedorById() throws ProveedorException {
        when(proveedorRepository.existsById(1L)).thenReturn(true);

        proveedorService.deleteById(1L);

        verify(proveedorRepository, times(1)).existsById(1L);
        verify(proveedorRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Debe lanzar excepción al eliminar proveedor inexistente")
    void shouldThrowWhenDeletingNonExistentProveedor() {
        when(proveedorRepository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> proveedorService.deleteById(99L))
                .isInstanceOf(ProveedorException.class)
                .hasMessageContaining("No se puede eliminar: proveedor con ID 99 no existe");

        verify(proveedorRepository, times(1)).existsById(99L);
        verify(proveedorRepository, never()).deleteById(any());
    }

    @Test
    @DisplayName("Debe listar proveedores por estado de suspensión")
    void shouldFindProveedoresBySuspencion() {
        when(proveedorRepository.findBySuspencion(false)).thenReturn(List.of(proveedorTest));

        List<Proveedor> result = proveedorService.findBySuspencion(false);

        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getSuspencion()).isFalse();

        verify(proveedorRepository, times(1)).findBySuspencion(false);
    }

}


