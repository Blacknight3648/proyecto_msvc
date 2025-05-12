package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "productos")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class ProductoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Long IdProducto;

    @Column(nullable = false)
    @NotBlank(message = "El campo nombre no puede estar vacio.")
    private String nombreProducto;

    @Column(nullable = false)
    @NotNull(message = "El campo precio no puede estar vacio.")
    private Integer precio;

    @Column(nullable = false)
    @NotBlank(message = "El campo descripcion no puede estar vacio.")
    private String descProducto;





}
