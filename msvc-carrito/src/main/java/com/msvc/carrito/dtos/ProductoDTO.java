package com.msvc.carrito.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
