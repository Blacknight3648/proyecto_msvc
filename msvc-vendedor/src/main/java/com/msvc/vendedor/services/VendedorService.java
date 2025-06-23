package com.msvc.vendedor.services;

import com.msvc.vendedor.dtos.VendedorDTO;
import com.msvc.vendedor.models.Vendedor;

import java.util.List;


public interface VendedorService {

    List<Vendedor> findAll();
    Vendedor findById(Long id);
    VendedorDTO findByRunVendedor(String runVendedor);
    Vendedor save(Vendedor vendedor);
    Vendedor deleteById(Long id);
    Vendedor update(Long id, VendedorDTO vendedorDTO);
    Vendedor suspend(Long id, VendedorDTO vendedorDTO);

}
