package com.mscv.proveedores.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProveedorDTO {

    private Long idProveedor;

    @Pattern(regexp = "\\d{1,8}-[\\dKk]", message = "El formato del run proveedor debe ser 11.111.111-X")
    private String runProveedor;

    @NotNull(message = "La razón Social no puede estar vacía")
    private String razonSocial;

    @NotNull(message = "suspencion no puede ser nulo")
    private Boolean suspencion;
}
