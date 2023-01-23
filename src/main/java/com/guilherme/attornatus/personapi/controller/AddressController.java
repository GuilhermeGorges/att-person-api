package com.guilherme.attornatus.personapi.controller;

import com.guilherme.attornatus.personapi.dto.AddressDTO;
import com.guilherme.attornatus.personapi.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
@Tag(name = "REST API for Address Registration")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @Operation(summary = "Get Addresses By Person ID")
    @GetMapping(value = "/{personId}")
    @ResponseStatus(HttpStatus.OK)
    public List<AddressDTO> getAddressesByPersonId(@PathVariable final Long personId) {
        return addressService.getAddressesByPersonId(personId);
    }
}
