package com.guilherme.attornatus.personapi.service;

import com.guilherme.attornatus.personapi.dto.PersonDTO;
import com.guilherme.attornatus.personapi.exception.exceptions.AddressNotFoundException;
import com.guilherme.attornatus.personapi.exception.exceptions.PersonAlreadyCreatedException;
import com.guilherme.attornatus.personapi.exception.exceptions.PersonNotFoundException;

import java.util.List;

public interface PersonService {
    List<PersonDTO> getAllPeople();

    PersonDTO getPersonByID(final Long personId) throws PersonNotFoundException;

    PersonDTO createPerson(final PersonDTO personReqDTO) throws PersonAlreadyCreatedException, AddressNotFoundException;
}
