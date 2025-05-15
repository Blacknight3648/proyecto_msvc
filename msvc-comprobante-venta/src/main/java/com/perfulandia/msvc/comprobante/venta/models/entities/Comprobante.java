package com.perfulandia.msvc.comprobante.venta.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comprobantes")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Comprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comprobante")
    private Long idComprobante;

    @Column(name = "hora_comprobante", nullable = false)
    @NotNull(message = "El campo hora comprobante no puede estar vacío")
    private LocalDateTime horaComprobante;

    @Column(nullable = false)
    @NotNull(message = "El campo costo no puede estar vacío")
    private Integer total;

    @Column(name = "id_sucursal")
    @NotNull(message = "El campo id sucursal no puede estar vacío")
    private Long idSucursal;

    @Column(name = "id_vendedor")
    @NotNull(message = "El campo id vendedor no puede estar vacío")
    private Long idVendedor;

    @Column(name = "id_cliente")
    private Long idCliente;

}
