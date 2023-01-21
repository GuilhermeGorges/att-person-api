package com.guilherme.attornatus.personapi.controller;

import com.guilherme.attornatus.personapi.dto.response.PersonDTO;
import com.guilherme.attornatus.personapi.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
@Api(value = "REST API for Person Registration")
@CrossOrigin(origins = "*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {
    private PersonService personService;

    @ApiOperation(value = "Get All People")
    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAllPeople() {
        return ResponseEntity.ok(personService.getAllPeople());
    }

}
