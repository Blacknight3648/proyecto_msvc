package com.msvc.model;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ComprobanteVenta {

    private LocalDateTime horaComprobante;
    private Integer total;
    private Long idSucursal;
    private Long idVendedor;
    private Long idCliente;
}
