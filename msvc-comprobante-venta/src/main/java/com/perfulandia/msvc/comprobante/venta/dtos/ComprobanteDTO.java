package com.perfulandia.msvc.comprobante.venta.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class ComprobanteDTO {

    private LocalDateTime horaComprobante;
    private Integer total;
    private VendedorDTO vendedor;
    private ClienteDTO cliente;
    private SucursalDTO sucursal;
    private CarritoDTO carrito;
    private Boolean factura;


}
