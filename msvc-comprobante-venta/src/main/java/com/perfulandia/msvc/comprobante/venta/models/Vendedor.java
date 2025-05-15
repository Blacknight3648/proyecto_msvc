package com.perfulandia.msvc.comprobante.venta.models;

import lombok.*;

import java.time.LocalDate;

@Getter@Setter@ToString@NoArgsConstructor@AllArgsConstructor
public class Vendedor {
    private Long idVendedor;
    private String runVendedor;
    private LocalDate fechaNacimiento;
    private String nombreCompleto;
}
