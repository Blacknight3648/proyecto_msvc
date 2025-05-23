package com.msvc.dtos;

import lombok.*;


@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class ProductoDTO {

    private Integer idProducto;
    private String nombreProducto;
    private Integer precio;
    private String descProducto;

}
