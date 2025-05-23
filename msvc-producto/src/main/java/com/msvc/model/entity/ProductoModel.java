package com.msvc.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "productos")
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @Column(nullable = false)
    @NotBlank(message = "El campo nombre no puede estar vacío.")
    private String nombre;

    @Column(nullable = false)
    @NotNull(message = "El campo precio no puede estar vacío.")
    private Integer precio;

    @Column(name = "desc_producto", nullable = false)
    @NotBlank(message = "El campo descripción no puede estar vacío.")
    private String descProducto;

}
