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
    public Object repository;

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
        // Puedes incluir lógica extra si es necesario, como establecer un estado inicial
        return this.proveedorRepository.save(proveedor);
    }

    @Override
    public Proveedores update(Proveedores proveedorDTO) {
        return null;
    }

    @Override
    public Proveedores suspend(Long id, ProveedorDTO proveedorDTO) {
        Proveedores proveedor = this.proveedorRepository.findById(id).orElseThrow(
                () -> new ProveedorException("El proveedor con id " + id + " no se encuentra en la base de datos")
        );

        // Ejemplo de campo "activo" para suspensión
        // Si tienes un campo como proveedor.setActivo(false), aquí lo usarías
        // Puedes también actualizar cualquier otro campo permitido por el DTO
        proveedor.setRazonSocial(proveedorDTO.getRazonSocial()); // ejemplo

        return this.proveedorRepository.save(proveedor);
    }

    @Override
    public ProveedorDTO save(ProveedorDTO proveedordDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }


}
