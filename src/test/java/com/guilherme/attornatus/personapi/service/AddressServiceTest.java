package com.guilherme.attornatus.personapi.service;

import com.guilherme.attornatus.personapi.builder.response.AddressResDTOBuilder;
import com.guilherme.attornatus.personapi.dto.response.AddressResDTO;
import com.guilherme.attornatus.personapi.entity.Address;
import com.guilherme.attornatus.personapi.mapper.AddressMapper;
import com.guilherme.attornatus.personapi.repository.AddressRepository;
import com.guilherme.attornatus.personapi.service.impl.AddressServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {
    private static final long VALID_PERSON_ID = 1L;
    @Mock
    private AddressRepository addressRepository;
    @InjectMocks
    private AddressServiceImpl addressService;
    private AddressMapper addressMapper = AddressMapper.INSTANCE;

    @Test
    void whenListOfAddressesIsCalledThenReturnListOfAddresses() {
        //given
        AddressResDTO expectedFoundAddressResDTO = AddressResDTOBuilder.builder().build().toAddressResDTO();
        Address expectedFoundAddress = addressMapper.toModel(expectedFoundAddressResDTO);

        //when
        when(addressRepository.findAllByPersonId(VALID_PERSON_ID))
                .thenReturn(Collections.singletonList(expectedFoundAddress));

        //then
        List<AddressResDTO> foundListAddressResDTO = addressService.getAddressesByPersonId(VALID_PERSON_ID);

        assertThat(foundListAddressResDTO, is(not(empty())));
        assertThat(foundListAddressResDTO.get(0), is(equalTo(expectedFoundAddressResDTO)));
    }

    @Test
    void whenListOfAddressesIsCalledThenReturnAnEmptyListOfAddresses() {
        //when
        when(addressRepository.findAllByPersonId(VALID_PERSON_ID)).thenReturn(Collections.EMPTY_LIST);

        //then
        List<AddressResDTO> foundListAddressDTO = addressService.getAddressesByPersonId(VALID_PERSON_ID);

        assertThat(foundListAddressDTO, is(empty()));

    }

}
