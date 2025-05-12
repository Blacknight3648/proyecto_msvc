package com.perfulandia.msvc_sucursal.repositories;

import com.perfulandia.msvc_sucursal.models.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
}
