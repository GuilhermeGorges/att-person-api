package com.guilherme.attornatus.personapi.controller;

import com.guilherme.attornatus.personapi.dto.response.PersonResDTO;
import com.guilherme.attornatus.personapi.exception.exceptions.PersonNotFoundException;
import com.guilherme.attornatus.personapi.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
@Tag(name = "REST API for Person Registration")
public class PersonController {
    @Autowired
    private PersonService personService;

    @Operation(summary = "Get All People")
    @GetMapping
    public ResponseEntity<List<PersonResDTO>> getAllPeople() {
        return ResponseEntity.ok(personService.getAllPeople());
    }

    @Operation(summary = "Get Person By ID")
    @GetMapping(value = "/{personId}")
    public ResponseEntity<PersonResDTO> getPersonByID(@PathVariable final Long personId) throws PersonNotFoundException {
        return ResponseEntity.ok(personService.getPersonByID(personId));
    }

}
