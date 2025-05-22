package com.msvc.carrito.dtos;

import org.springframework.web.bind.annotation.RestController;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@RestController
public class CarritoDTO {
    private VendedorDTO vendedor;
    private ClienteDTO cliente;
    private ProductoDTO producto;
}
