package com.guilherme.attornatus.personapi.controller;

import com.guilherme.attornatus.personapi.builder.AddressDTOBuilder;
import com.guilherme.attornatus.personapi.dto.AddressDTO;
import com.guilherme.attornatus.personapi.service.AddressService;
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

import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AddressControllerTest {
    private static final long VALID_PERSON_ID = 1L;

    private static final String ADDRESS_API_URL_PATH = "/api/v1/address";

    private MockMvc mockMvc;
    @Mock
    private AddressService addressService;
    @InjectMocks
    private AddressController addressController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(addressController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenGETListIsCalledWithValidPeopleIDThenListAddressesReturned() throws Exception {
        // given
        AddressDTO addressDTO = AddressDTOBuilder.builder().build().toAddressDTO();

        //when
        when(addressService.getAddressesByPersonId(VALID_PERSON_ID))
                .thenReturn(Collections.singletonList(addressDTO));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(ADDRESS_API_URL_PATH + "/" + VALID_PERSON_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(addressDTO.getId().intValue())))
                .andExpect(jsonPath("$[0].cep", is(addressDTO.getCEP())))
                .andExpect(jsonPath("$[0].city", is(addressDTO.getCity())))
                .andExpect(jsonPath("$[0].logradouro", is(addressDTO.getLogradouro())))
                .andExpect(jsonPath("$[0].number", is(addressDTO.getNumber().intValue())));
    }

    @Test
    void whenGETListWithValidPeopleIDIsCalledThenOkStatusIsReturned() throws Exception {
        //given
        AddressDTO addressDTO = AddressDTOBuilder.builder().build().toAddressDTO();

        //when
        when(addressService.getAddressesByPersonId(VALID_PERSON_ID))
                .thenReturn(Collections.singletonList(addressDTO));

        //then
        mockMvc.perform(MockMvcRequestBuilders.get(ADDRESS_API_URL_PATH + "/" + VALID_PERSON_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
