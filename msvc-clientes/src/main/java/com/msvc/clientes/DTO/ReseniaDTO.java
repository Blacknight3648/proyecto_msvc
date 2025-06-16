package com.msvc.clientes.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReseniaDTO {
    private Long id;                            // ID de la reseña
    private Long idProducto;                    // ID del producto al que pertenece    private Long idCliente;
    private Integer calificacion;               // Calificación (por ejemplo, de 1 a 5)
    private String comentario;                  // Comentario del usuario
    private Long idCliente;                     // SE REGISTRA EL CLIENTE QUE REALIZÓ LA RESENIA
    private LocalDateTime fechaHoraResenia;     // SE DEBE REGISTRAR LA HORA DE LA RESENIA
}
