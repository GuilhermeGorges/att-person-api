package com.guilherme.attornatus.personapi.service;

import com.guilherme.attornatus.personapi.builder.PersonDTOBuilder;
import com.guilherme.attornatus.personapi.dto.PersonDTO;
import com.guilherme.attornatus.personapi.entity.Person;
import com.guilherme.attornatus.personapi.exception.exceptions.AddressNotFoundException;
import com.guilherme.attornatus.personapi.exception.exceptions.PersonAlreadyCreatedException;
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
    void whenPersonInformedThenItShouldBeCreated() throws PersonAlreadyCreatedException, AddressNotFoundException {
        // given
        PersonDTO expectedPersonDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        Person expectedSavedPerson = personMapper.toModel(expectedPersonDTO);

        // when
        when(personRepository.findByCPF(expectedPersonDTO.getCPF())).thenReturn(Optional.empty());
        when(personRepository.save(expectedSavedPerson)).thenReturn(expectedSavedPerson);

        //then
        PersonDTO createdPersonDTO = personService.createPerson(expectedPersonDTO);

        assertThat(createdPersonDTO.getId(), is(equalTo(expectedPersonDTO.getId())));
        assertThat(createdPersonDTO.getName(), is(equalTo(expectedPersonDTO.getName())));
        assertThat(createdPersonDTO.getCPF(), is(equalTo(expectedPersonDTO.getCPF())));
        assertThat(createdPersonDTO.getMainAddress(), is(equalTo(expectedPersonDTO.getMainAddress())));
        assertThat(createdPersonDTO.getBirthDate(), is(equalTo(expectedPersonDTO.getBirthDate())));
    }

    @Test
    void whenAlreadyRegisteredPersonCPFThenAnExceptionShouldBeThrown() {
        // given
        PersonDTO expectedPersonDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        Person duplicatedPerson = personMapper.toModel(expectedPersonDTO);

        // when
        when(personRepository.findByCPF(expectedPersonDTO.getCPF())).thenReturn(Optional.of(duplicatedPerson));

        // then
        assertThrows(PersonAlreadyCreatedException.class, () -> personService.createPerson(expectedPersonDTO));
    }

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

    @Test
    void whenValidPersonIDIsGivenThenReturnAPerson() throws PersonNotFoundException {
        // given
        PersonDTO expectedFoundPersonDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        Person expectedFoundPerson = personMapper.toModel(expectedFoundPersonDTO);

        // when
        when(personRepository.findById(expectedFoundPerson.getId())).thenReturn(Optional.of(expectedFoundPerson));

        // then
        PersonDTO foundPersonDTO = personService.getPersonByID(expectedFoundPersonDTO.getId());

        assertThat(foundPersonDTO, is(equalTo(expectedFoundPersonDTO)));
    }

    @Test
    void whenNotRegisteredPersonIdIsGivenThenThrowAnException() {
        // given
        PersonDTO expectedFoundPersonDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        // when
        when(personRepository.findById(expectedFoundPersonDTO.getId())).thenReturn(Optional.empty());

        // then
        assertThrows(PersonNotFoundException.class, () -> personService.getPersonByID(expectedFoundPersonDTO.getId()));
    }
}
