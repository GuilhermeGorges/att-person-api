package com.guilherme.attornatus.personapi.mapper;

import com.guilherme.attornatus.personapi.dto.response.PersonDTO;
import com.guilherme.attornatus.personapi.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}
