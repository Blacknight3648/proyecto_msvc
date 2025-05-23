package com.msvc.vendedor.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "vendedores")
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vendedor")
    private Long idVendedor;

    @Column(name = "run_vendedor", nullable = false)
    @Pattern(regexp = "\\d{1,8}-[\\dKk]", message = "El formato del run cliente debe ser 11.111.111-X")
    private String runVendedor;

    @Column(name = "fecha_nacimiento", nullable = false)
    @NotNull(message = "El campo fecha de nacimiento no puede estar vacio")
    private LocalDate fechaNacimiento;

    @Column(name = "nombre_completo",nullable = false)
    @NotBlank(message = "El campo nombre completo de vendedor no puede estar vacio")
    private String nombreCompleto;

    @Column(name = "estado_cuenta")
    @NotNull(message = "El campo estado de cuenta no puede ser nulo")
    private boolean estadoCuenta;


}
