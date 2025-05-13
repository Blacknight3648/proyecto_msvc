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

    @Column(nullable = false, unique = true)
    private String nombreRol;


    //Se usa Set y no List porque así se evitan elementos repetidos
    //Es decir, elimina la redundancia de datos
    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> usuarios;
}

