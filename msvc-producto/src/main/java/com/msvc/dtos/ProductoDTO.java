package com.msvc.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    private Long idProducto;     // cambia a Long para coincidir con entidad
    private String nombre;       // usar mismo nombre que en entidad
    private Integer precio;
    private String descProducto;

}
