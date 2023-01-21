package com.guilherme.attornatus.personapi.service;

import com.guilherme.attornatus.personapi.dto.response.AddressDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    List<AddressDTO> getAddressesByPersonId(final Long personId);
}
