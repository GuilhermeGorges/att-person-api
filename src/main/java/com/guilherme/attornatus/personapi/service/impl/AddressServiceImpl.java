package com.guilherme.attornatus.personapi.service.impl;

import com.guilherme.attornatus.personapi.dto.AddressDTO;
import com.guilherme.attornatus.personapi.dto.PersonDTO;
import com.guilherme.attornatus.personapi.entity.Address;
import com.guilherme.attornatus.personapi.exception.exceptions.AddressNotFoundException;
import com.guilherme.attornatus.personapi.exception.exceptions.PersonAlreadyCreatedException;
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
    public List<AddressDTO> getAddressesByPersonId(final Long personId) {
        return addressRepository.findAllByPersonId(personId).stream()
                .map(addressMapper::toDTO)
                .collect(Collectors.toList());
    }

    protected void verifyIfAddressAlreadyExists(Long id) throws AddressNotFoundException {
        addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
    }
}
