package com.guilherme.attornatus.personapi.controller;

import com.guilherme.attornatus.personapi.dto.PersonDTO;
import com.guilherme.attornatus.personapi.exception.exceptions.AddressNotFoundException;
import com.guilherme.attornatus.personapi.exception.exceptions.PersonAlreadyCreatedException;
import com.guilherme.attornatus.personapi.exception.exceptions.PersonNotFoundException;
import com.guilherme.attornatus.personapi.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
@Tag(name = "REST API for Person Registration")
public class PersonController {
    @Autowired
    private PersonService personService;

    @Operation(summary = "Create Person")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDTO createPerson(@RequestBody @Valid final PersonDTO personDTO) throws PersonAlreadyCreatedException, AddressNotFoundException {
        return personService.createPerson(personDTO);
    }

    @Operation(summary = "Get All People")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDTO> getAllPeople() {
        return personService.getAllPeople();
    }

    @Operation(summary = "Get Person By ID")
    @GetMapping(value = "/{personId}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDTO getPersonByID(@PathVariable final Long personId) throws PersonNotFoundException {
        return personService.getPersonByID(personId);
    }

    @Operation(summary = "Update Person")
    @PutMapping(value = "/{personId}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDTO updatePerson(@PathVariable final Long personId, @RequestBody @Valid final PersonDTO personDTO) throws PersonNotFoundException, AddressNotFoundException {
        return personService.updatePerson(personId, personDTO);
    }

}
