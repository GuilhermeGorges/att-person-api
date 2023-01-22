package com.guilherme.attornatus.personapi.builder.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Builder;
import com.guilherme.attornatus.personapi.dto.response.AddressDTO;
import com.guilherme.attornatus.personapi.dto.response.PersonDTO;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Builder
public class PersonDTOBuilder {
    @Builder.Default
    private Long id = 1L;
    @Builder.Default
    private String name = "Larissa Abraão";
    @Builder.Default
    private String CPF = "03171313030";
    @Builder.Default
    private LocalDate birthDate = LocalDate.parse("2022-09-09");
    @Builder.Default
    private AddressDTO mainAddress = null;

    public PersonDTO toPersonDTO() {
        return new PersonDTO(id,
                name,
                CPF,
                birthDate,
                mainAddress);
    }
}
