package com.msvc.clientes.models;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Column(name="run_cliente",nullable = false, unique = true)
    @NotNull(message = "El campo run no puede ser nulo")
    @NotBlank(message = "El campo run no puede estar vacio")
    @Pattern(regexp = "\\d{1,8}-[\\dKk]", message = "El formato del run cliente debe ser 11111111-X")
    private String runCliente;

    @Column(name="fecha_nacimiento", nullable = false)
    @NotNull(message = "El campo fecha de nacimiento no puede ser nulo")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @Column(name = "nombre_completo",nullable = false)
    @NotBlank(message = "El campo nombre completo de cliente no puede estar vacio")
    private String nombreCompleto;

    @Column(name = "correo_cliente", nullable = false)
    @NotNull(message = "El campo correo no puede ser nulo")
    @NotBlank(message = "El campo correo no puede estar vacio")
    private  String correoCliente;

    @Column(name = "estado_cuenta")
    @NotNull(message = "El campo estado de cuenta no puede ser nulo")
    private boolean estadoCuenta;

}
