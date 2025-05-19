package com.msvc.carrito.dtos;


import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@ToString @NoArgsConstructor @AllArgsConstructor
public class ClienteDTO {


    private Long idCliente;
    private String runCliente;
    private LocalDate fechaNacimiento;
    private String nombreCompleto;
    private boolean estadoCuenta;
}
