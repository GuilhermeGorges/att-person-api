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
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;

import static com.guilherme.attornatus.personapi.utils.JsonConversionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
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
    void whenPOSTIsCalledThenAnAddressIsCreated() throws Exception {
        //given
        AddressDTO addressDTO = AddressDTOBuilder.builder().build().toAddressDTO();

        //when
        when(addressService.createAddress(addressDTO))
                .thenReturn(addressDTO);

        //then
        mockMvc.perform(post(ADDRESS_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(addressDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(addressDTO.getId().intValue())))
                .andExpect(jsonPath("$.cep", is(addressDTO.getCEP())))
                .andExpect(jsonPath("$.city", is(addressDTO.getCity())))
                .andExpect(jsonPath("$.logradouro", is(addressDTO.getLogradouro())))
                .andExpect(jsonPath("$.number", is(addressDTO.getNumber().intValue())));
    }

    @Test
    void whenGETListIsCalledWithValidPeopleIDThenListAddressesReturned() throws Exception {
        //given
        AddressDTO addressDTO = AddressDTOBuilder.builder().build().toAddressDTO();

        //when
        when(addressService.getAddressesByPersonId(VALID_PERSON_ID))
                .thenReturn(Collections.singletonList(addressDTO));

        //then
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
