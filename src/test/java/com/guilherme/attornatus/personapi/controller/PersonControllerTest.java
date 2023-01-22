package com.guilherme.attornatus.personapi.controller;

import com.guilherme.attornatus.personapi.builder.response.PersonDTOBuilder;
import com.guilherme.attornatus.personapi.dto.response.PersonDTO;
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
    void whenGETListWithPeopleIsCalledThenOkStatusIsReturned() throws Exception {
        //given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        //when
        when(personService.getAllPeople()).thenReturn(Collections.singletonList(personDTO));

        //then
        mockMvc.perform(MockMvcRequestBuilders.get(PERSON_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name",is(personDTO.getName())))
                .andExpect(jsonPath("$[0].birthDate",is(personDTO.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))))
                .andExpect(jsonPath("$[0].cpf",is(personDTO.getCPF())))
                .andExpect(jsonPath("$[0].mainAddress",is(personDTO.getMainAddress())));

    }

    @Test
    void whenGETListWithoutPeopleIsCalledThenOkStatusIsReturned() throws Exception {
        //given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        //when
        when(personService.getAllPeople()).thenReturn(Collections.singletonList(personDTO));

        //then
        mockMvc.perform(MockMvcRequestBuilders.get(PERSON_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
