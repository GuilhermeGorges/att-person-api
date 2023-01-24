package com.guilherme.attornatus.personapi.service.impl;

import com.guilherme.attornatus.personapi.dto.AddressDTO;
import com.guilherme.attornatus.personapi.entity.Address;
import com.guilherme.attornatus.personapi.entity.Person;
import com.guilherme.attornatus.personapi.exception.exceptions.PersonNotFoundException;
import com.guilherme.attornatus.personapi.mapper.AddressMapper;
import com.guilherme.attornatus.personapi.repository.AddressRepository;
import com.guilherme.attornatus.personapi.repository.PersonRepository;
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
    private PersonRepository personRepository;

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO) throws PersonNotFoundException {
        Address address = addressMapper.toModel(addressDTO);
        address.setCEP(OperationServer.formatData(addressDTO.getCEP()));
        address.setPerson(verifyPerson(addressDTO.getPerson()));
        Address savedAddress = addressRepository.save(address);
        return addressMapper.toDTO(savedAddress);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressDTO> getAddressesByPersonId(final Long personId) {
        return addressRepository.findAllByPersonId(personId).stream()
                .map(addressMapper::toDTO)
                .collect(Collectors.toList());
    }

    private Person verifyPerson(Person person) throws PersonNotFoundException {
        Long personId = person.getId();
        if(person.getId() == null || person.getName() == null || person.getCPF() == null) {
            person = personRepository.findById(personId)
                    .orElseThrow(() -> new PersonNotFoundException(personId));
        }
        return person;
    }

}
