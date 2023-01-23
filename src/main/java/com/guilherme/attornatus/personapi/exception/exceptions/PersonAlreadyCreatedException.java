package com.guilherme.attornatus.personapi.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PersonAlreadyCreatedException extends Exception{
    public PersonAlreadyCreatedException(String cpf) { super("Pessoa já cadastrada com o cpf: " + cpf + " .");
    }
}
