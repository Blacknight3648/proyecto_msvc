package com.perfulandia.msvc.comprobante.venta.models;

import lombok.*;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor@ToString
public class Carrito {
    private Long idCarrito;
    private Integer precioTotal;
    private String cupon;
    private Long idCliente;
    private Long idVendedor;
}
