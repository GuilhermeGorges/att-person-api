package com.guilherme.attornatus.personapi.service.impl;

import com.guilherme.attornatus.personapi.dto.PersonDTO;
import com.guilherme.attornatus.personapi.entity.Person;
import com.guilherme.attornatus.personapi.exception.exceptions.AddressNotFoundException;
import com.guilherme.attornatus.personapi.exception.exceptions.PersonAlreadyCreatedException;
import com.guilherme.attornatus.personapi.exception.exceptions.PersonNotFoundException;
import com.guilherme.attornatus.personapi.mapper.PersonMapper;
import com.guilherme.attornatus.personapi.repository.AddressRepository;
import com.guilherme.attornatus.personapi.repository.PersonRepository;
import com.guilherme.attornatus.personapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;
    private AddressRepository addressRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Override
    public List<PersonDTO> getAllPeople() {
        return personRepository.findAll().stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO getPersonByID(final Long personId) throws PersonNotFoundException {
        PersonDTO personDTO = personRepository.findById(personId)
                .map(personMapper::toDTO)
                .orElseThrow(() -> new PersonNotFoundException(personId));
        return personDTO;
    }

    @Override
    public PersonDTO createPerson(PersonDTO personDTO) throws PersonAlreadyCreatedException, AddressNotFoundException {
        personDTO.setCPF(OperationServer.formatData(personDTO.getCPF()));
        verifyIfPersonAlreadyExists(personDTO.getCPF());
        Long mainAddressId = personDTO.getMainAddress();
        if (mainAddressId != null) {
            if (addressRepository.findById(mainAddressId).isEmpty()){
                throw new AddressNotFoundException(mainAddressId);
            }
        }
        Person person = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(person);
        return personMapper.toDTO(savedPerson);
    }

    private void verifyIfPersonAlreadyExists(String personCPF) throws PersonAlreadyCreatedException {
        Optional<Person> optionalPerson = personRepository.findByCPF(personCPF);
        if (optionalPerson.isPresent()) {
            throw new PersonAlreadyCreatedException(personCPF);
        }
    }

}
