package com.mscv.proveedores.Repository;

import com.mscv.proveedores.model.Proveedores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedores, Long> {
    
}
