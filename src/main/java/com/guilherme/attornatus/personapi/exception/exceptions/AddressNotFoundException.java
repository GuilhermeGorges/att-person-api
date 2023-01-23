package com.guilherme.attornatus.personapi.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AddressNotFoundException extends Exception {
    public AddressNotFoundException(Long id) {
        super("O endereço informado não foi encontrado");
    }
}
