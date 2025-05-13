package com.msvc.clientes.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="clientes")
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_cliente")
    private Long idCliente;

    @Column(name="fecha_nacimiento", nullable = false)
    @NotNull(message = "El campo fecha de nacimiento no puede estar vacio")
    private LocalDate fechaNacimiento;

    @Column(name="run_cliente",nullable = false)
    @Pattern(regexp = "\\d{1,8}-[\\dKk]", message = "El formato del run cliente debe ser 11.111.111-X")
    private String runCliente;

    @Column(name = "nombre_completo",nullable = false)
    @NotBlank(message = "El campo nombre completo cliente no puede estar vacio")
    private String nombreCompleto;












}
