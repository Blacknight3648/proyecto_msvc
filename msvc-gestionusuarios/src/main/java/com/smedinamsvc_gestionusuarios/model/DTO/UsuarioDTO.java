package com.smedinamsvc_gestionusuarios.model.DTO;

import lombok.Data;
import java.time.LocalDate;

import jakarta.validation.constraints.Email;

@Data
@Email //Anotación para validar un Email
public class UsuarioDTO {

    private Integer rutUsuario;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private String nombreUsuario;
    private Email correoElectronico;

    // Notamos que *no* incluimos la contraseña aquí
}
