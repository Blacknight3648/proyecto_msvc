package com.msvc.carrito.model;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Vendedor {

    private Long IdVendedor;
    private String runVendedor;
    private LocalDate fechaNacimiento;
    private String nombreCompleto;
    private boolean estadoCuenta;

}
