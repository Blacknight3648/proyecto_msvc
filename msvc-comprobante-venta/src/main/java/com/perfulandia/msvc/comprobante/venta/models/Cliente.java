package com.perfulandia.msvc.comprobante.venta.models;


import lombok.*;

import java.time.LocalDate;

@Getter@AllArgsConstructor@NoArgsConstructor@Setter@ToString
public class Cliente {
    private Long idCliente;
    private String runCliente;
    private LocalDate fechaNacimiento;
    private String nombreCompleto;
}
