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
    public VendedorDTO findByRunVendedor(String runVendedor) {
        String run = runVendedor.toUpperCase();

        Vendedor vendedor = vendedorRepository.findByRunVendedor(run).orElseThrow(()-> new VendedorException("El vendedor con el rut "+ run + " no esta en la base de datos"));

        VendedorDTO vendedorDTO =  new VendedorDTO();
        vendedorDTO.setRunVendedor(vendedor.getRunVendedor());
        vendedorDTO.setCorreoVendedor(vendedor.getCorreoVendedor());
        vendedorDTO.setNombreCompleto(vendedor.getNombreCompleto());
        vendedorDTO.setFechaNacimiento(vendedor.getFechaNacimiento());
        vendedorDTO.setEstadoCuenta(vendedor.isEstadoCuenta());

        return vendedorDTO;
    }

    @Override
    public Vendedor save(Vendedor vendedor) {
        vendedor.setEstadoCuenta(true);

        if(!this.vendedorRepository.findByRunVendedor(vendedor.getRunVendedor()).isPresent()){
            return vendedorRepository.save(vendedor);
        }else{
            throw new RuntimeException();
        }
    }

    @Override
    public Vendedor deleteById(Long id) {
        if (!vendedorRepository.existsById(id)){
            throw new VendedorException("No se puede eliminar: vendedor no esta en sistema");
        }

        return null;
    }

    @Override
    public Vendedor update(Long id, VendedorDTO vendedorDTO) {
        Vendedor vendedor = this.vendedorRepository.findById(id).orElseThrow(() -> new VendedorException("El vendedor con id "+ id +" no se encuentra en la base de datos"));

        vendedor.setFechaNacimiento(vendedorDTO.getFechaNacimiento());
        vendedor.setCorreoVendedor(vendedorDTO.getCorreoVendedor());
        vendedor.setNombreCompleto(vendedorDTO.getNombreCompleto());

        Vendedor update = vendedorRepository.save(vendedor);

        return update;


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
