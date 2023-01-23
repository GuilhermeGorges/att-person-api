package com.guilherme.attornatus.personapi.mapper;

import com.guilherme.attornatus.personapi.dto.response.AddressResDTO;
import com.guilherme.attornatus.personapi.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address toModel(AddressResDTO addressDTO);

    AddressResDTO toDTO(Address address);

}
