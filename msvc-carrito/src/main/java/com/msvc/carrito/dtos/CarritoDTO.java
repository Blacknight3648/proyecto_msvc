package com.msvc.carrito.dtos;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CarritoDTO {
    private VendedorDTO vendedor;
    private ClienteDTO cliente;
    private ProductoDTO producto;
}
