package com.guilherme.attornatus.personapi.service;

import com.guilherme.attornatus.personapi.dto.AddressDTO;
import com.guilherme.attornatus.personapi.exception.exceptions.PersonNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    List<AddressDTO> getAddressesByPersonId(final Long personId);

    AddressDTO createAddress(final AddressDTO addressDTO) throws PersonNotFoundException;
}
