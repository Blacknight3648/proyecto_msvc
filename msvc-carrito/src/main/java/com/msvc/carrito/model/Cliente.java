package com.msvc.carrito.model;

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
