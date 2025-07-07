package com.mscv.proveedores.Repository;

import com.mscv.proveedores.model.Proveedor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    List<Proveedor> findBySuspencion(boolean suspencion);

}
