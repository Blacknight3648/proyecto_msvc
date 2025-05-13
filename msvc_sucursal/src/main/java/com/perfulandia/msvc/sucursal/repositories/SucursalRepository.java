package com.perfulandia.msvc.sucursal.repositories;

import com.perfulandia.msvc.sucursal.models.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
}
