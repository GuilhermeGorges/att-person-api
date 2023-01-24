package com.guilherme.attornatus.personapi.exception;

import com.guilherme.attornatus.personapi.exception.exceptions.AddressNotFoundException;
import com.guilherme.attornatus.personapi.exception.exceptions.PersonAlreadyCreatedException;
import com.guilherme.attornatus.personapi.exception.exceptions.PersonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ExceptionBuilder> personNotFoundException(PersonNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionBuilder(HttpStatus.NOT_FOUND, e.getMessage())
        );
    }

    @ExceptionHandler(PersonAlreadyCreatedException.class)
    public ResponseEntity<ExceptionBuilder> personAlreadyCreatedException(PersonAlreadyCreatedException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionBuilder(HttpStatus.BAD_REQUEST, e.getMessage())
        );
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ExceptionBuilder> addressNotFoundException(AddressNotFoundException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionBuilder(HttpStatus.BAD_REQUEST, e.getMessage())
        );
    }

}
