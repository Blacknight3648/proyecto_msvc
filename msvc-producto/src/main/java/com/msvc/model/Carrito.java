package com.msvc.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carritos")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // id único para cada carrito

    @Column(nullable = false)
    private String producto;

    @Column(nullable = false)
    private String vendedor;

    @Column(nullable = false)
    private String cliente;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Integer preciototal;

    private String cupon;

    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;

    @Column(name = "id_vendedor", nullable = false)
    private Long idVendedor;
}
