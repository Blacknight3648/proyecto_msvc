package com.smedinamsvc.resenia.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClienteDTO {

    private Long idCliente;
    private String runCliente;
    private LocalDate fechaNacimiento;
    private String nombreCompleto;
    private  String correoCliente;
    private boolean estadoCuenta;
}
