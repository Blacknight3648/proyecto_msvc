package com.msvc.productos.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
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
