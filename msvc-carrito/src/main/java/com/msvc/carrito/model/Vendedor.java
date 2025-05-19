package com.msvc.carrito.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Vendedor {

    private String runVendedor;
    private LocalDate fechaNacimiento;
    private String nombreCompleto;
    private boolean estadoCuenta;

}
