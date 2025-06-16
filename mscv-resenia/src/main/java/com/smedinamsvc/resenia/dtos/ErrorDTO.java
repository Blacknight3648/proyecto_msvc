package com.smedinamsvc.resenia.dtos;

import lombok.*;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDTO {

    private int status; //Esto mostrará el estado de la respuesta (200, 400, etc)
    private Date date; //Muestra la fecha de la excepción
    private Map<String, String> errors; //Mostrará el error

    @Override
    public String toString() {
        return "{" +
                "status=" + status +
                ", date=" + date +
                ", errors=" + errors +
                '}';
    }

}
