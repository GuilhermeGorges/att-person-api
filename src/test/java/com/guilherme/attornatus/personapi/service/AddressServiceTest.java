package com.guilherme.attornatus.personapi.service;

import com.guilherme.attornatus.personapi.builder.AddressDTOBuilder;
import com.guilherme.attornatus.personapi.dto.AddressDTO;
import com.guilherme.attornatus.personapi.entity.Address;
import com.guilherme.attornatus.personapi.exception.exceptions.PersonNotFoundException;
import com.guilherme.attornatus.personapi.mapper.AddressMapper;
import com.guilherme.attornatus.personapi.repository.AddressRepository;
import com.guilherme.attornatus.personapi.service.impl.AddressServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AddressServiceTest {
    private static final Long VALID_PERSON_ID = 1L;
    @Mock
    private AddressRepository addressRepository;
    @InjectMocks
    private AddressServiceImpl addressService;
    private AddressMapper addressMapper = AddressMapper.INSTANCE;

    @Test
    void whenAddressInformedThenItShouldBeCreated() throws PersonNotFoundException {
        //given
        AddressDTO expectedAddressDTO = AddressDTOBuilder.builder().build().toAddressDTO();
        Address expectedSavedAddress = addressMapper.toModel(expectedAddressDTO);

        //when
        when(addressRepository.findById(expectedAddressDTO.getId())).thenReturn(Optional.empty());
        when(addressRepository.save(expectedSavedAddress)).thenReturn(expectedSavedAddress);

        //then
        AddressDTO createdAddressDTO = addressService.createAddress(expectedAddressDTO);

        assertThat(createdAddressDTO.getId(), is(equalTo(expectedSavedAddress.getId())));
        assertThat(createdAddressDTO.getCEP(), is(equalTo(expectedSavedAddress.getCEP())));
        assertThat(createdAddressDTO.getCity(), is(equalTo(expectedSavedAddress.getCity())));
        assertThat(createdAddressDTO.getLogradouro(), is(equalTo(expectedSavedAddress.getLogradouro())));
        assertThat(createdAddressDTO.getNumber(), is(equalTo(expectedSavedAddress.getNumber())));
        assertThat(createdAddressDTO.getPerson(), is(equalTo(expectedSavedAddress.getPerson())));
    }

    @Test
    void whenListOfAddressesIsCalledThenReturnListOfAddresses() {
        //given
        AddressDTO expectedFoundAddressDTO = AddressDTOBuilder.builder().build().toAddressDTO();
        Address expectedFoundAddress = addressMapper.toModel(expectedFoundAddressDTO);

        //when
        when(addressRepository.findAllByPersonId(VALID_PERSON_ID))
                .thenReturn(Collections.singletonList(expectedFoundAddress));

        //then
        List<AddressDTO> foundListAddressDTO = addressService.getAddressesByPersonId(VALID_PERSON_ID);

        assertThat(foundListAddressDTO, is(not(empty())));
        assertThat(foundListAddressDTO.get(0), is(equalTo(expectedFoundAddressDTO)));
    }

    @Test
    void whenListOfAddressesIsCalledThenReturnAnEmptyListOfAddresses() {
        //when
        when(addressRepository.findAllByPersonId(VALID_PERSON_ID)).thenReturn(Collections.EMPTY_LIST);

        //then
        List<AddressDTO> foundListAddressDTO = addressService.getAddressesByPersonId(VALID_PERSON_ID);

        assertThat(foundListAddressDTO, is(empty()));

    }

}
