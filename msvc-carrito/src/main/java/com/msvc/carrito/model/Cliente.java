package com.msvc.carrito.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    private String runCliente;
    private LocalDate fechaNacimiento;
    private String nombreCompleto;
    private boolean estadoCuenta;
}
