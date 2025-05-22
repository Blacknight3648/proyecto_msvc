package com.smedina_msvcproveedor.DTO;

import java.util.Date;
import java.util.Map;

public class ErrorDTO {

    private Integer status;

    private final Map<String, String> errors;

    public ErrorDTO(Map<String, String> errors) {
        this.errors = errors;
    }

    @Override
    public String toString(){

        return "{" +
                "Status = " + status +
                "\nError = "+ errors +
                "}";

    }

}
