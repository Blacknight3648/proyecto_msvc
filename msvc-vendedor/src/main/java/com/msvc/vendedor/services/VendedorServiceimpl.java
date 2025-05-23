package com.msvc.vendedor.services;

import com.msvc.vendedor.dtos.VendedorDTO;
import com.msvc.vendedor.exception.VendedorException;
import com.msvc.vendedor.models.Vendedor;
import com.msvc.vendedor.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorServiceimpl implements VendedorService{

    @Autowired
    private VendedorRepository vendedorRepository;

    @Override
    public List<Vendedor> findAll() {
        return this.vendedorRepository.findAll();
    }

    @Override
    public Vendedor findById(Long id) {
        return this.vendedorRepository.findById(id).orElseThrow(
                ()-> new VendedorException("El vendedor con id "+ id + " no se encuentra en la base de datos")

        );
    }

    @Override
    public Vendedor save(Vendedor vendedor) {
        vendedor.setEstadoCuenta(true);
        return vendedorRepository.save(vendedor);
    }

    @Override
    public Vendedor suspend(Long id, VendedorDTO vendedorDTO) {

        Vendedor vendedor = this.vendedorRepository.findById(id).orElseThrow(
                () -> new VendedorException("El vendedor con id "+ id + " no se encuentra en la base de datos")
        );

        vendedor.setEstadoCuenta(vendedorDTO.isEstadoCuenta());

        Vendedor update = vendedorRepository.save(vendedor);

        return update;

    }
}
