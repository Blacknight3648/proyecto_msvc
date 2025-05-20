package com.msvc.productos.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ComprobanteDTO {

    private LocalDateTime horaComprobante;
    private Integer total;


}
