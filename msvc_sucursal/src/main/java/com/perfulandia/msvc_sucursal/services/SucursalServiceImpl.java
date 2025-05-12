package com.perfulandia.msvc_sucursal.services;

import com.perfulandia.msvc_sucursal.exceptions.SucursalException;
import com.perfulandia.msvc_sucursal.models.Sucursal;
import com.perfulandia.msvc_sucursal.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalServiceImpl implements SucursalService{
    @Autowired
    private SucursalRepository sucursalRepository;


    @Override
    public List<Sucursal> findAll() {
        return this.sucursalRepository.findAll();
    }

    @Override
    public Sucursal findById(Long id) {
        return this.sucursalRepository.findById(id).orElseThrow(
                () -> new SucursalException("La sucursal con id "+id+" no existe")
        );
    }

    @Override
    public Sucursal save(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }
}
