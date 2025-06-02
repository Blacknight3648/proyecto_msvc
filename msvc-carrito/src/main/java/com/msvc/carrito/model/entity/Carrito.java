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
    private Long idProducto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "preciototal", nullable = false)
    private Integer precioTotal;

    @Column(name = "cupon", nullable = true)
    private String cupon;

    @Column(name="id_cliente", nullable = false)
    @NotNull(message = "El campo id_cliente no puede estar vacio")
    private Long idCliente;

    @Column(name="id_vendedor", nullable = false)
    @NotNull(message = "El campo id_vendedor no puede estar vacio")
    private Long idVendedor;

    @Column(name = "factura", nullable = false)
    @NotNull(message = "Debe especificar si es o no factura")
    private Boolean factura;


}
