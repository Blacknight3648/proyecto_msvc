package model;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private Long idCliente;
    private LocalDate fechaNacimiento;
    private String runCliente;
    private String nombreCompleto;
}
