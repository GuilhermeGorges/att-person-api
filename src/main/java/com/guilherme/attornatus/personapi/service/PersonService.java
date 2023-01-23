package com.guilherme.attornatus.personapi.service;

import com.guilherme.attornatus.personapi.dto.response.PersonResDTO;
import com.guilherme.attornatus.personapi.exception.exceptions.PersonNotFoundException;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<PersonResDTO> getAllPeople();

    PersonResDTO getPersonByID(final Long personId) throws PersonNotFoundException;
}
