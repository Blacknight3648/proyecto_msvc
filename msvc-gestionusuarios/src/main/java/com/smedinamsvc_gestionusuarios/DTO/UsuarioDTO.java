package com.smedinamsvc_gestionusuarios.DTO;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;

@Data
@ToString
@Email //Anotación para validar un Email
public class UsuarioDTO {

    private Integer rutUsuario;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private String nombreUsuario;
    private Email correoElectronico;

}
