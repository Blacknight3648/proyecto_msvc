package com.msvc.clientes.DTO;

import com.msvc.clientes.models.Cliente;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class ClienteDTO extends Cliente {

    private String runCliente;
    private LocalDate fechaNacimiento;
    private String nombreCompleto;
    private String correoCliente;
    private boolean estadoCuenta;


}
