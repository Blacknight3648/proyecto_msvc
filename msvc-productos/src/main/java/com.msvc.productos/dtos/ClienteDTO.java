package com.msvc.productos.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private LocalDate fechaNacimiento;
    private String runCliente;
    private String nombreCompleto;
}
