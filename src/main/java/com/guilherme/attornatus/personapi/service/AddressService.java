package com.guilherme.attornatus.personapi.service;

import com.guilherme.attornatus.personapi.dto.response.AddressResDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    List<AddressResDTO> getAddressesByPersonId(final Long personId);
}
