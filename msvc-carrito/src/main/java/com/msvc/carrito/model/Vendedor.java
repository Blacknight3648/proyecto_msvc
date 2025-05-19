package com.msvc.carrito.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class Vendedor {

    private LocalDate fechaNacimiento;
    private String nombreCompleto;
    private boolean estadoCuenta;

}
