package com.perfulandia.msvc.comprobante.venta.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VendedorDTO {

    private String runVendedor;
    private LocalDate fechaNacimiento;
    private String nombreCompleto;
}
