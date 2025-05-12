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
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private String nombreUsuario;
    private String correoElectronico;
    //Se usa Set y no List porque así se evitan elementos repetidos
    //Es decir, elimina la redundancia de datos
    private Set<String> roles; // Solo guarda los nombres de los roles
}
