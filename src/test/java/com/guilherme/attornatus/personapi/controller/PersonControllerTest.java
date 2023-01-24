package com.guilherme.attornatus.personapi.controller;

import com.guilherme.attornatus.personapi.builder.PersonDTOBuilder;
import com.guilherme.attornatus.personapi.dto.PersonDTO;
import com.guilherme.attornatus.personapi.exception.exceptions.AddressNotFoundException;
import com.guilherme.attornatus.personapi.exception.exceptions.PersonNotFoundException;
import com.guilherme.attornatus.personapi.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;
import java.util.List;

import static com.guilherme.attornatus.personapi.utils.JsonConversionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PersonControllerTest {
    private static final String PERSON_API_URL_PATH = "/api/v1/person";
    private static final List DATE_VALIDATION = List.of(2022,9,9);
    private static final Long INVALID_ADDRESS_ID = 10L;
    private static final Long VALID_PERSON_ID = 1L;
    private static final String ALTERNATIVE_PERSON_NAME = "Eleanor Mendonça";
    private static final String ALTERNATIVE_CPF = "641.822.710-76";

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
    void whenPOSTIsCalledThenAPersonIsCreated() throws Exception {
        //given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        //when
        when(personService.createPerson(personDTO)).thenReturn(personDTO);

        //then
        mockMvc.perform(post(PERSON_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(personDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(personDTO.getName())))
                .andExpect(jsonPath("$.birthDate", is(DATE_VALIDATION)))
                .andExpect(jsonPath("$.cpf", is(personDTO.getCPF())))
                .andExpect(jsonPath("$.mainAddress", is(personDTO.getMainAddress())));
    }

    @Test
    void whenPOSTIsCalledWithInvalidAddressIDThenNotFoundStatusIsReturned() throws Exception {
        //given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        personDTO.setMainAddress(INVALID_ADDRESS_ID);

        //when
        when(personService.createPerson(personDTO)).thenThrow(AddressNotFoundException.class);

        //then
        mockMvc.perform(post(PERSON_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(personDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGETListWithPeopleIsCalledThenListOfPeopleIsReturned() throws Exception {
        //given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        //when
        when(personService.getAllPeople()).thenReturn(Collections.singletonList(personDTO));

        //then
        mockMvc.perform(get(PERSON_API_URL_PATH)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name",is(personDTO.getName())))
                .andExpect(jsonPath("$[0].birthDate",is(DATE_VALIDATION)))
                .andExpect(jsonPath("$[0].cpf",is(personDTO.getCPF())))
                .andExpect(jsonPath("$[0].mainAddress",is(personDTO.getMainAddress())));

    }

    @Test
    void whenGETListWithPeopleIsCalledThenOkStatusIsReturned() throws Exception {
        //given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        //when
        when(personService.getAllPeople()).thenReturn(Collections.singletonList(personDTO));

        //then
        mockMvc.perform(get(PERSON_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenGETIsCalledWithValidPersonIDThenOkStatusIsReturned() throws Exception {
        //given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        //when
        when(personService.getPersonByID(personDTO.getId())).thenReturn(personDTO);

        //then
        mockMvc.perform(get(PERSON_API_URL_PATH + "/" + personDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(personDTO.getName())))
                .andExpect(jsonPath("$.birthDate",is(DATE_VALIDATION)))
                .andExpect(jsonPath("$.cpf",is(personDTO.getCPF())))
                .andExpect(jsonPath("$.mainAddress",is(personDTO.getMainAddress())));
    }

    @Test
    void whenGETIsCalledWithRegisteredPersonIDThenOkStatusIsReturned() throws Exception {
        //given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        //when
        when(personService.getPersonByID(personDTO.getId()))
                .thenReturn(personDTO);

        //then
        mockMvc.perform(get(PERSON_API_URL_PATH + "/" + personDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenGETIsCalledWithoutRegisteredPersonIDThenNotFoundStatusIsReturned() throws Exception {
        //given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        //when
        when(personService.getPersonByID(personDTO.getId())).thenThrow(PersonNotFoundException.class);

        //then
        mockMvc.perform(get(PERSON_API_URL_PATH + "/" + personDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenPUTIsCalledThenAPersonIsUpdated() throws Exception {
        //given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        PersonDTO personUpdatedDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        personUpdatedDTO.setName(ALTERNATIVE_PERSON_NAME);
        personUpdatedDTO.setCPF(ALTERNATIVE_CPF);

        //when
        when(personService.createPerson(personDTO)).thenReturn(personDTO);
        when(personService.updatePerson(VALID_PERSON_ID, personUpdatedDTO)).thenReturn(personUpdatedDTO);

        //then
        mockMvc.perform(put(PERSON_API_URL_PATH + "/" + VALID_PERSON_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(personUpdatedDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(personUpdatedDTO.getName())))
                .andExpect(jsonPath("$.birthDate", is(DATE_VALIDATION)))
                .andExpect(jsonPath("$.cpf", is(personUpdatedDTO.getCPF())))
                .andExpect(jsonPath("$.mainAddress", is(personUpdatedDTO.getMainAddress())));
    }

}
