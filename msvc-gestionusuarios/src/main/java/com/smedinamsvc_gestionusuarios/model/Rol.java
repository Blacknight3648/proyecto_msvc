package com.smedinamsvc_gestionusuarios.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_rol", nullable = false, unique = true)
    private String nombreRol;

    @ManyToMany(mappedBy = "roles")
    private Set<Usuarios> usuarios;
}

