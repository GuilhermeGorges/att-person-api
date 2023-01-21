package com.guilherme.attornatus.personapi.service;

import com.guilherme.attornatus.personapi.dto.response.PersonDTO;

import java.util.List;

public interface PersonService {
    List<PersonDTO> getAllPeople();
}
