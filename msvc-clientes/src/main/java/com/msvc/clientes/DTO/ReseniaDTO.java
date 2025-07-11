package com.msvc.clientes.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReseniaDTO {
    private Long id;
    private Long productoId;
    private Integer valoracion;
    private String resenia;
    private Long idCliente;
}
