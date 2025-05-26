package com.msvc.carrito.dtos;

import lombok.*;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.RestController;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@RestController
public class ClienteDTO {

    private String runCliente;
    private LocalDate fechaNacimiento;
    private String nombreCompleto;
    private boolean estadoCuenta;

    public boolean isEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(boolean estadoCuenta){
        this.estadoCuenta= estadoCuenta;

    }
}
