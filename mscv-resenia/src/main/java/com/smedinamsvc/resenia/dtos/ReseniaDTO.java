package com.smedinamsvc.resenia.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReseniaDTO {
    private Long id;
    private Long productoId;
    private Integer valoracion;
    private String resenia;
    private Long idCliente;
}
