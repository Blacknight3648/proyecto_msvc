package com.perfulandia.msvc.sucursal.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Entity
@Table(name="sucursales")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @ToString
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal")
    private Long idSucursal;

    @Column(name = "nombre_sucursal", nullable = false)
    @NotBlank(message = "El campo nombre sucursal no puede estar vacio")
    private String nombreSucursal;

    @Column(name = "direccion_sucursal", nullable = false)
    @NotBlank(message = "El campo direccion sucursal no puede estar vacio")
    private String direccionSucursal;



}
