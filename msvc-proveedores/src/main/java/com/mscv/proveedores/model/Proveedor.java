package com.mscv.proveedores.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "proveedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Long idProveedor;

    @Column(name = "run_proveedor", nullable = false, unique = true)
    @Pattern(regexp = "\\d{1,8}-[\\dKk]", message = "El formato del run cliente debe ser 11.111.111-X")
    private String runCliente;

    @Column(name = "razon_social", nullable = false)
    @NotNull(message = "La razón Social no puede estar vacía")
    private String razonSocial;
}

