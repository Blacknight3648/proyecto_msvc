package com.smedinamsvc.resenia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table (name= "resenia")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Resenia {

    //ID DE LA TABLA: SE AUTOGENERA DE FOMRA AUTOMATICA
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="id_resenia")
    private Long id;

    //ID DEL PRODUCTO AL QUE SE VA A HACER LA RESENIA
    @Column(name = "producto_id", nullable = false)
    @NotNull(message = "El campo del id producto no puede estar vacio")
    private Long productoId;

    //LA VALORACION DEL PRODUCTO DEL 1 AL 5
    @Column(name = "valoracion", nullable = false)
    @NotNull(message = "El campo de valoracion no puede estar vacío y solo se puede agregar una valoracion del 1 al 5")
    //El @Min ayuda a que solo acepte datos en cierto rango
    @Min(value = 1, message = "La valoración mínima permitida es 1")
    // @Max ayuda a que se limite la valoracion hasta el 5
    @Max(value = 5, message = "La valoración máxima permitida es 5")
    private Integer valoracion;

    //SE DEBE AGREGAR LA RESENIA COMO COMENTARIO
    @Column(name = "resenia", nullable = false)
    @NotBlank(message = "El campo de reseña, no puede estar vacío")
    private String resenia;

    // SE REGISTRA EL CLIENTE QUE REALIZÓ LA RESENIA
    @Column(name = "id_cliente", nullable = false)
    @NotNull(message = "El ID del cliente no puede estar vacio")
    private Long idCliente;

}
