package com.msvc.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comprobantes_venta")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class ComprobanteVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único para cada comprobante

    @Column(name = "hora_comprobante", nullable = false)
    private LocalDateTime horaComprobante;

    @Column(nullable = false)
    private Integer total;

    @Column(name = "id_sucursal", nullable = false)
    private Long idSucursal;

    @Column(name = "id_vendedor", nullable = false)
    private Long idVendedor;

    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;
}
