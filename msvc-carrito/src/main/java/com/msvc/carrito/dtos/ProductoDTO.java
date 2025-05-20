package com.msvc.carrito.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {


    private String nombreProducto;
    private Integer precio;
    private String descProducto;
    private Long idCarrito;
    private Long idComprobante;

}
