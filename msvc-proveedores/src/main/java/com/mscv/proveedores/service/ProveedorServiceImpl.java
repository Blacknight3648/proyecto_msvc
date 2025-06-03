package com.mscv.proveedores.service;

import com.mscv.proveedores.DTO.ProveedorDTO;
import com.mscv.proveedores.Exceptions.ProveedorException;
import com.mscv.proveedores.Repository.ProveedorRepository;
import com.mscv.proveedores.model.Proveedores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public List<Proveedores> findAll() {
        return this.proveedorRepository.findAll();
    }

    @Override
    public Proveedores findById(Long id) {
        return this.proveedorRepository.findById(id).orElseThrow(
                () -> new ProveedorException("El proveedor con id " + id + " no se encuentra en la base de datos")
        );
    }

    @Override
    public Proveedores save(Proveedores proveedor) {
        return this.proveedorRepository.save(proveedor);
    }

    @Override
    public void deleteById(Long id) {
        if (!proveedorRepository.existsById(id)){
            throw new ProveedorException("No se puede eliminar: proovedor no encontrado");
        }
        proveedorRepository.deleteById(id);
    }


    @Override
    public Proveedores suspend(Long id, ProveedorDTO proveedorDTO) {
        Proveedores proveedor = this.proveedorRepository.findById(id).orElseThrow(
                () -> new ProveedorException("El proveedor con id " + id + " no se encuentra en la base de datos")
        );
        proveedor.setSuspencion(proveedorDTO.getSuspencion());//
        return this.proveedorRepository.save(proveedor);
    }


    @Override
    public Proveedores update(Long id, ProveedorDTO proveedorDTO) {
        Proveedores proveedor = this.proveedorRepository.findById(id).orElseThrow(
                () -> new ProveedorException("El proveedor con id " + id + " no se encuentra en la base de datos")
        );
        proveedor.setRazonSocial(proveedorDTO.getRazonSocial());
        proveedor.setRunProveedor(proveedorDTO.getRunProveedor());

        return this.proveedorRepository.save(proveedor);
    }


}
