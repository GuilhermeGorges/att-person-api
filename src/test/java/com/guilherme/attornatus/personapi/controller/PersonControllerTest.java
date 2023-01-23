package com.guilherme.attornatus.personapi.controller;

import com.guilherme.attornatus.personapi.builder.response.PersonResDTOBuilder;
import com.guilherme.attornatus.personapi.dto.response.PersonResDTO;
import com.guilherme.attornatus.personapi.exception.exceptions.PersonNotFoundException;
import com.guilherme.attornatus.personapi.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.time.format.DateTimeFormatter;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {

    private static final String PERSON_API_URL_PATH = "/api/v1/person";

    private MockMvc mockMvc;
    @Mock
    private PersonService personService;
    @InjectMocks
    private PersonController personController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(personController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenGETListWithPeopleIsCalledThenListOfPeopleIsReturned() throws Exception {
        //given
        PersonResDTO personResDTO = PersonResDTOBuilder.builder().build().toPersonResDTO();

        //when
        when(personService.getAllPeople()).thenReturn(Collections.singletonList(personResDTO));

        //then
        mockMvc.perform(MockMvcRequestBuilders.get(PERSON_API_URL_PATH)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name",is(personResDTO.getName())))
                .andExpect(jsonPath("$[0].birthDate",is(personResDTO.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))))
                .andExpect(jsonPath("$[0].cpf",is(personResDTO.getCPF())))
                .andExpect(jsonPath("$[0].mainAddress",is(personResDTO.getMainAddress())));

    }

    @Test
    void whenGETListWithPeopleIsCalledThenOkStatusIsReturned() throws Exception {
        //given
        PersonResDTO personResDTO = PersonResDTOBuilder.builder().build().toPersonResDTO();

        //when
        when(personService.getAllPeople()).thenReturn(Collections.singletonList(personResDTO));

        //then
        mockMvc.perform(MockMvcRequestBuilders.get(PERSON_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenGETIsCalledWithValidPersonIDThenOkStatusIsReturned() throws Exception, PersonNotFoundException {
        // given
        PersonResDTO personResDTO = PersonResDTOBuilder.builder().build().toPersonResDTO();

        //when
        when(personService.getPersonByID(personResDTO.getId())).thenReturn(personResDTO);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(PERSON_API_URL_PATH + "/" + personResDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(personResDTO.getName())))
                .andExpect(jsonPath("$.birthDate",is(personResDTO.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))))
                .andExpect(jsonPath("$.cpf",is(personResDTO.getCPF())))
                .andExpect(jsonPath("$.mainAddress",is(personResDTO.getMainAddress())));
    }

    @Test
    void whenGETIsCalledWithRegisteredPersonIDThenOkStatusIsReturned() throws Exception, PersonNotFoundException {
        // given
        PersonResDTO personResDTO = PersonResDTOBuilder.builder().build().toPersonResDTO();

        //when
        when(personService.getPersonByID(personResDTO.getId()))
                .thenReturn(personResDTO);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(PERSON_API_URL_PATH + "/" + personResDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenGETIsCalledWithoutRegisteredPersonIDThenNotFoundStatusIsReturned() throws Exception {
        // given
        PersonResDTO personResDTO = PersonResDTOBuilder.builder().build().toPersonResDTO();

        //when
        when(personService.getPersonByID(personResDTO.getId())).thenThrow(PersonNotFoundException.class);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(PERSON_API_URL_PATH + "/" + personResDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
