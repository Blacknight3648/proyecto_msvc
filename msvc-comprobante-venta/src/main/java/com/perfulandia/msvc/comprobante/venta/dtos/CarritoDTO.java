package com.perfulandia.msvc.comprobante.venta.dtos;

import lombok.*;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString
public class CarritoDTO {
    private Long idProducto;
    private Integer cantidad;
    private Integer precioTotal;
    private String cupon;
}
