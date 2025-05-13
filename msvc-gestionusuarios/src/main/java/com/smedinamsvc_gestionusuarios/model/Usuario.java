package com.smedinamsvc_gestionusuarios.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer rutUsuario;

    @Column(name = "nombreCompleto", nullable = false)
    private String nombres;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "nombre_usuario", nullable = false, unique = true)
    private String nombreUsuario;

    @Column(name = "correo_electronico", nullable = false, unique = true)
    private String correoElectronico;

    @Column(nullable = false)
    private String contrasenia;

    @Column(nullable = false)
    private boolean activo = true; // nuevo campo para saber si el usuario está suspendido o no

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "usuario_roles",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles;

}




