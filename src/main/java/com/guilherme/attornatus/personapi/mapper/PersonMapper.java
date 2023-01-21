package com.guilherme.attornatus.personapi.mapper;

import com.guilherme.attornatus.personapi.dto.response.PersonDTO;
import com.guilherme.attornatus.personapi.entity.Person;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper {

    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}
