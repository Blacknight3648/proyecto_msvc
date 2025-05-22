package com.smedina_msvcproveedor.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProveedorDTO {

    private Long idProveedor;

    @Pattern(regexp = "\\d{1,8}-[\\dKk]", message = "El formato del run cliente debe ser 11.111.111-X")
    private String runCliente;

    @NotNull(message = "La razón Social no puede estar vacía")
    private String razonSocial;
}
