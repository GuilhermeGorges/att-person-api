package com.guilherme.attornatus.personapi.mapper;

import com.guilherme.attornatus.personapi.dto.response.AddressDTO;
import com.guilherme.attornatus.personapi.entity.Address;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {

    Address toModel(AddressDTO addressDTO);

    AddressDTO toDTO(Address address);

}
