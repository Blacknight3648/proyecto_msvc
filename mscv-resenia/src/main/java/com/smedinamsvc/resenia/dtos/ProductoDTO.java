package com.smedinamsvc.resenia.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductoDTO {

    private Long idProducto;
    private String nombreProducto;
    private Integer precio;
    private String descProducto;

}
