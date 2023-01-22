package com.guilherme.attornatus.personapi.service;

import com.guilherme.attornatus.personapi.builder.response.PersonDTOBuilder;
import com.guilherme.attornatus.personapi.dto.response.PersonDTO;
import com.guilherme.attornatus.personapi.entity.Person;
import com.guilherme.attornatus.personapi.mapper.PersonMapper;
import com.guilherme.attornatus.personapi.repository.PersonRepository;
import com.guilherme.attornatus.personapi.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PersonServiceImpl personService;
    private PersonMapper personMapper = PersonMapper.INSTANCE;

    @Test
    void whenListOfPeopleIsCalledThenReturnListOfPeople() {
        //given
        PersonDTO expectedFoundPersonDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        Person expectedFoundPerson = personMapper.toModel(expectedFoundPersonDTO);

        //when
        when(personRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundPerson));

        //then
        List<PersonDTO> foundListPersonDTO = personService.getAllPeople();

        assertThat(foundListPersonDTO, is(not(empty())));
        assertThat(foundListPersonDTO.get(0), is(equalTo(expectedFoundPersonDTO)));
    }

    @Test
    void whenListOfPeopleIsCalledThenReturnAnEmptyListOfPeople() {
        //when
        when(personRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        //then
        List<PersonDTO> foundListPersonDTO = personService.getAllPeople();

        assertThat(foundListPersonDTO, is(empty()));

    }

}
