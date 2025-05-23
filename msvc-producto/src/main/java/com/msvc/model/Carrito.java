package com.msvc.model;

import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class Carrito {


    private String producto;
    private String vendedor;
    private String cliente;
    private Integer cantidad;
    private Integer preciototal;
    private String cupon;
    private Long idCliente;
    private Long idVendedor;
}
