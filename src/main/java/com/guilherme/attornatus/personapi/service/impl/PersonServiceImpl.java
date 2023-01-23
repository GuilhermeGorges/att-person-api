package com.guilherme.attornatus.personapi.service.impl;

import com.guilherme.attornatus.personapi.dto.response.PersonResDTO;
import com.guilherme.attornatus.personapi.exception.exceptions.PersonNotFoundException;
import com.guilherme.attornatus.personapi.mapper.PersonMapper;
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
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Override
    public List<PersonResDTO> getAllPeople() {
        return personRepository.findAll().stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PersonResDTO getPersonByID(final Long personId) throws PersonNotFoundException {
        PersonResDTO personResDTO = personRepository.findById(personId)
                .map(personMapper::toDTO)
                .orElseThrow(() -> new PersonNotFoundException(personId));
        return personResDTO;
    }

}
