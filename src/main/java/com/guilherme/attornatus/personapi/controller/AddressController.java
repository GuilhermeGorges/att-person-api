package com.guilherme.attornatus.personapi.controller;

import com.guilherme.attornatus.personapi.dto.response.AddressDTO;
import com.guilherme.attornatus.personapi.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
@Api(value = "REST API for Person Registration")
@CrossOrigin(origins = "*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AddressController {
    private AddressService addressService;

    @ApiOperation(value = "Get Addresses By Person ID")
    @GetMapping(value = "/{personId}")
    public ResponseEntity<List<AddressDTO>> getAddressesByPersonId(@PathVariable final Long personId) {
        return ResponseEntity.ok(addressService.getAddressesByPersonId(personId));
    }
}
