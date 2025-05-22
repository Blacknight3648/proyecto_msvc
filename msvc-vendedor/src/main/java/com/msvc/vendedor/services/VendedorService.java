package com.msvc.vendedor.services;

import com.msvc.vendedor.models.Vendedor;

import java.util.List;


public interface VendedorService {

    List<Vendedor> findAll();
    Vendedor findById(Long id);
    Vendedor save(Vendedor vendedor);
    Vendedor suspend(Vendedor vendedor);

}
