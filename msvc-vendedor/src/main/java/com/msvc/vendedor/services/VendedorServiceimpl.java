package com.msvc.vendedor.services;

import com.msvc.vendedor.exception.VendedorException;
import com.msvc.vendedor.models.Vendedor;
import com.msvc.vendedor.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    public Vendedor suspend(Vendedor vendedor) {

        vendedor.setEstadoCuenta(false);
        return this.vendedorRepository.findById(vendedor.getIdVendedor()).orElseThrow(

                ()->new VendedorException("El vendedor con id "+ vendedor.getIdVendedor()+" no se encuentra en la base de datos")

        );
    }
}
