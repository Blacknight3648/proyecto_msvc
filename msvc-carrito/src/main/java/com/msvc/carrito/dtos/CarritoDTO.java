package com.msvc.carrito.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class CarritoDTO {


    private String producto;
    private Integer cantidad;
    private Integer preciototal;
    private String cupon;
}
