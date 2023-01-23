package com.guilherme.attornatus.personapi.service.impl;

import com.guilherme.attornatus.personapi.dto.response.AddressResDTO;
import com.guilherme.attornatus.personapi.mapper.AddressMapper;
import com.guilherme.attornatus.personapi.repository.AddressRepository;
import com.guilherme.attornatus.personapi.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;
    private final AddressMapper addressMapper = AddressMapper.INSTANCE;

    @Override
    @Transactional(readOnly = true)
    public List<AddressResDTO> getAddressesByPersonId(final Long personId) {
        return addressRepository.findAllByPersonId(personId).stream()
                .map(addressMapper::toDTO)
                .collect(Collectors.toList());
    }
}
