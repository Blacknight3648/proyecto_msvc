package com.perfulandia.msvc.sucursal.services;

import com.perfulandia.msvc.sucursal.exceptions.SucursalException;
import com.perfulandia.msvc.sucursal.models.Sucursal;
import com.perfulandia.msvc.sucursal.repositories.SucursalRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SucursalServiceTests {

    @Mock
    private SucursalRepository sucursalRepository;

    @InjectMocks
    private SucursalServiceImpl sucursalServiceImpl;

    private Sucursal sucursalPrueba;
    private List<Sucursal> sucursales = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        Faker faker = new Faker(Locale.of("es","CL"));
        for (int i=0;i<100;i++){
            Sucursal sucursal = new Sucursal();
            sucursal.setIdSucursal((long)i);
            sucursal.setNombreSucursal(faker.company().name());
            sucursal.setDireccionSucursal(faker.address().fullAddress());

            this.sucursales.add(sucursal);
        }

        this.sucursalPrueba = new Sucursal(
                1L,"Cousinio","Valparaiso Cousinio"
        );
    }

    @Test
    @DisplayName("Debe listar todos las sucursales")
    public void shouldFindAllSucursales(){

        this.sucursales.add(this.sucursalPrueba);

        when(sucursalRepository.findAll()).thenReturn(this.sucursales);

        List<Sucursal> result = sucursalServiceImpl.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(this.sucursalPrueba);

        verify(sucursalRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe buscar una sucursal por id")
    public void shouldFindSucursalById(){

        when(sucursalRepository.findById(1L)).thenReturn(Optional.of(this.sucursalPrueba));

        Sucursal result = sucursalServiceImpl.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.sucursalPrueba);

        verify(sucursalRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("No debe buscar una sucursal por id")
    public void shouldNotFindSucursalById(){
        Long idInexistente = 999L;
        when(sucursalRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            sucursalServiceImpl.findById(idInexistente);
        }).isInstanceOf(SucursalException.class)
                .hasMessageContaining("La sucursal con id "+ idInexistente +" no se encuentra en la base de datos");
        verify(sucursalRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar sucursal")
    public void shouldSaveSucursal(){
        when(sucursalRepository.save(any(Sucursal.class))).thenReturn(this.sucursalPrueba);

        Sucursal result = sucursalServiceImpl.save()
    }
}
