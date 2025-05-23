package com.msvc.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReseniaDTO {

    private Long id;                 // ID de la reseña
    private String comentario;       // Comentario del usuario
    private Integer calificacion;    // Calificación (por ejemplo, de 1 a 5)
    private Long idProducto;         // ID del producto al que pertenece
    private Long idCliente;          // ID del cliente que la escribió
}