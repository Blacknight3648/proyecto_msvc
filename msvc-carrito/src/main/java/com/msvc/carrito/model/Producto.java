package com.msvc.carrito.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Producto {

    private String nombreProducto;
    private Integer precio;
    private String descProducto;
}
