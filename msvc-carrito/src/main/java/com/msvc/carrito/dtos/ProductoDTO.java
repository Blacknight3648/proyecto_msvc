package com.msvc.carrito.dtos;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RestController
public class ProductoDTO {

    private Long idProducto;
    private String nombreProducto;
    private Integer precio;
    private String descProducto;


    
}
