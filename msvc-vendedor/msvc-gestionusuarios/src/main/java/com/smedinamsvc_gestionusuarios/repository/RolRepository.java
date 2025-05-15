package com.smedinamsvc_gestionusuarios.repository;

import com.smedinamsvc_gestionusuarios.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    
}


