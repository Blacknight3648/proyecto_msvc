package com.msvc.carrito.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    private Long IdProducto;
    private String nombreProducto;
    private Integer precio;
    private String descProducto;
}
