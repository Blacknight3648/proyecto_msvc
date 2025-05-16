package com.smedinamsvc.resenia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="id_resenia")
    private Integer id;

    @Column(name = "producto_id", nullable = false)
    private Integer productoId;

    @Column(name = "resenia", nullable = false)
    @NotBlank(message = "El campo de reseña, no puede estar vacío")
    private String resenia;


}
