package com.msvc.productos.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class ProductoDTO {

    private Integer idProducto;
    private String nombreProducto;
    private Integer precio;
    private String descProducto;

}
