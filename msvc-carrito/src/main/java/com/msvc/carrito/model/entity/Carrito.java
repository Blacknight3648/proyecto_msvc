package com.msvc.carrito.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name="carrito")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito")
    private Long idCarrito;

    @Column(name = "producto", nullable = false)
    @NotNull(message = "El campo producto no puede estar vacio")
    private String producto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "preciototal", nullable = false)
    private Integer preciototal;

    @Column(name = "cupon", nullable = true)
    private String cupon;


}
