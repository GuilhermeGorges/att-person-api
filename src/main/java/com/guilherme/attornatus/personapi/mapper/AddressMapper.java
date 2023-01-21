package com.guilherme.attornatus.personapi.mapper;

import com.guilherme.attornatus.personapi.dto.response.AddressDTO;
import com.guilherme.attornatus.personapi.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address toModel(AddressDTO addressDTO);

    AddressDTO toDTO(Address address);

}
