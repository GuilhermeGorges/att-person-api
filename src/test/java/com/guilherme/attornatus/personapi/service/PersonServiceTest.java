package com.guilherme.attornatus.personapi.service;

import com.guilherme.attornatus.personapi.builder.response.PersonResDTOBuilder;
import com.guilherme.attornatus.personapi.dto.response.PersonResDTO;
import com.guilherme.attornatus.personapi.entity.Person;
import com.guilherme.attornatus.personapi.exception.exceptions.PersonNotFoundException;
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
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        PersonResDTO expectedFoundPersonResDTO = PersonResDTOBuilder.builder().build().toPersonResDTO();
        Person expectedFoundPerson = personMapper.toModel(expectedFoundPersonResDTO);

        //when
        when(personRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundPerson));

        //then
        List<PersonResDTO> foundListPersonResDTO = personService.getAllPeople();

        assertThat(foundListPersonResDTO, is(not(empty())));
        assertThat(foundListPersonResDTO.get(0), is(equalTo(expectedFoundPersonResDTO)));
    }

    @Test
    void whenListOfPeopleIsCalledThenReturnAnEmptyListOfPeople() {
        //when
        when(personRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        //then
        List<PersonResDTO> foundListPersonResDTO = personService.getAllPeople();

        assertThat(foundListPersonResDTO, is(empty()));
    }

    @Test
    void whenValidPersonIDIsGivenThenReturnAPerson() throws PersonNotFoundException {
        // given
        PersonResDTO expectedFoundPersonResDTO = PersonResDTOBuilder.builder().build().toPersonResDTO();
        Person expectedFoundPerson = personMapper.toModel(expectedFoundPersonResDTO);

        // when
        when(personRepository.findById(expectedFoundPerson.getId())).thenReturn(Optional.of(expectedFoundPerson));

        // then
        PersonResDTO foundPersonResDTO = personService.getPersonByID(expectedFoundPersonResDTO.getId());

        assertThat(foundPersonResDTO, is(equalTo(expectedFoundPersonResDTO)));
    }

    @Test
    void whenNotRegisteredPersonIdIsGivenThenThrowAnException() {
        // given
        PersonResDTO expectedFoundPersonResDTO = PersonResDTOBuilder.builder().build().toPersonResDTO();

        // when
        when(personRepository.findById(expectedFoundPersonResDTO.getId())).thenReturn(Optional.empty());

        // then
        assertThrows(PersonNotFoundException.class, () -> personService.getPersonByID(expectedFoundPersonResDTO.getId()));
    }
}
