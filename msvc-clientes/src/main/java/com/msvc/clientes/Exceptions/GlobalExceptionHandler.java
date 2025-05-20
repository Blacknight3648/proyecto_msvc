package com.msvc.clientes.Exceptions;

import com.msvc.clientes.DTO.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ErrorDTO createErrorDTO(int status, Date date, Map<String, String> errorMap){

        ErrorDTO errorDTO = new ErrorDTO();

        errorDTO.setStatus(status);
        errorDTO.setDate(date);
        errorDTO.setErrors(errorMap);

        return errorDTO;

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationFields(MethodArgumentNotValidException exception){

        Map<String, String> errorMap =  new HashMap<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()){

            errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());

        }

        return ResponseEntity.status(400)
                .body(this.createErrorDTO(HttpStatus.BAD_REQUEST.value(),new Date(),errorMap));

    }

    @ExceptionHandler(ClienteException.class)
    public ResponseEntity<ErrorDTO> handleVendedorException(ClienteException exception){

        if (exception.getMessage().contains("no se encuentra en la base de datos")){
            Map<String, String> errorMap = Collections.singletonMap("Cliente no encontrada", exception.getMessage());
            return ResponseEntity.status(404)
                    .body(this.createErrorDTO(HttpStatus.CONFLICT.value(), new Date(), errorMap));
        }else{

            Map<String, String> errorMap = Collections.singletonMap("Cliente existe", exception.getMessage());
            return ResponseEntity.status(409)
                    .body(this.createErrorDTO(HttpStatus.CONTINUE.value(), new Date(), errorMap));

        }

    }

}
