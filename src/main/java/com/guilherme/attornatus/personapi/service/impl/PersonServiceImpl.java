package com.guilherme.attornatus.personapi.service.impl;

import com.guilherme.attornatus.personapi.dto.response.PersonDTO;
import com.guilherme.attornatus.personapi.mapper.PersonMapper;
import com.guilherme.attornatus.personapi.repository.PersonRepository;
import com.guilherme.attornatus.personapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Override
    public List<PersonDTO> getAllPeople() {
        return personRepository.findAll().stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }
}
