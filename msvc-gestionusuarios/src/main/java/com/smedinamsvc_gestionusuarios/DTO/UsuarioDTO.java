package com.smedinamsvc_gestionusuarios.DTO;

import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long id;
    private Integer rutUsuario;
    private String nombres;
    private LocalDate fechaNacimiento;
    private String nombreUsuario;
    private String correoElectronico;
    private String contrasenia;
    private Set<String> roles; // Solo guarda los nombres de los roles
    private boolean activo;    // Nuevo campo para saber si está suspendido o no
}

