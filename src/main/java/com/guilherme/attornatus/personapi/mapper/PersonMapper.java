package com.guilherme.attornatus.personapi.mapper;

import com.guilherme.attornatus.personapi.dto.response.PersonResDTO;
import com.guilherme.attornatus.personapi.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person toModel(PersonResDTO personDTO);

    PersonResDTO toDTO(Person person);
}
