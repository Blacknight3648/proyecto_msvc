package com.smedina_msvcproveedor.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Long idProveedor;

    @Column(name="run_proveedor",nullable = false)
    @Pattern(regexp = "\\d{1,8}-[\\dKk]", message = "El formato del run cliente debe ser 11.111.111-X")
    private String runCliente;

    @Column(name= "razon_social", nullable = false)
    @NotNull(message = "La razón Social no puede estar vacía")
    private String razonSocial;
    

}
