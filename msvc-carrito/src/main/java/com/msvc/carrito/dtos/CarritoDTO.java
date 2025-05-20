package com.msvc.carrito.dtos;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class CarritoDTO {


    private String producto;
    private String vendedor;
    private String cliente;
    private Integer cantidad;
    private Integer preciototal;
    private String cupon;
}
