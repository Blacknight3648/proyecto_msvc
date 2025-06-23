package com.perfulandia.msvc.comprobante.venta.dtos;

import lombok.*;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@ToString
public class CarritoDTO {
    private Integer cantidad;
    private Integer precioTotal;
    private String cupon;
}

// Setear por el id del producto (Para poder modificar)
