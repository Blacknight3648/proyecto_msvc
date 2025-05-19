package com.msvc.carrito.dtos;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VendedorDTO {

    private Long idVendedor;
    private String runVendedor;
    private LocalDate fechaNacimiento;
    private String nombreCompleto;
    private boolean estadoCuenta;


}
