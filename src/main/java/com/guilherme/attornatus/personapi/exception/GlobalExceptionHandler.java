package com.guilherme.attornatus.personapi.exception;

import com.guilherme.attornatus.personapi.dto.exception.ExceptionDTO;
import com.guilherme.attornatus.personapi.exception.exceptions.PersonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ExceptionDTO> transferenceNotFoundException(PersonNotFoundException e){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionDTO(HttpStatus.NOT_FOUND, e.getMessage())
        );
    }
}
