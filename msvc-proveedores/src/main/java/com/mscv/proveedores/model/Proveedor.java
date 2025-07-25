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
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Long idProveedor;

    @Column(name = "run_proveedor", nullable = false, unique = true)
    @Pattern(regexp = "\\d{7,8}-[\\dkK]", message = "El formato del run Proveedor debe ser 12345678-K")
    private String runProveedor;

    @Column(name = "razon_social", nullable = false)
    @NotNull(message = "La razón Social no puede estar vacía")
    private String razonSocial;

    @Column(name = "suspencion", nullable = false)
    @NotNull(message = "El campo suspencion no puede estar nulo")
    private Boolean suspencion;

    
}

