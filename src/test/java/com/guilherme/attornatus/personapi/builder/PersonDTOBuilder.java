package com.guilherme.attornatus.personapi.builder;

import lombok.Builder;
import com.guilherme.attornatus.personapi.dto.PersonDTO;

import java.time.LocalDate;

@Builder
public class PersonDTOBuilder {
    @Builder.Default
    private Long id = 1L;
    @Builder.Default
    private String name = "Larissa Abraão";
    @Builder.Default
    private String CPF = "03171313030";
    @Builder.Default
    private LocalDate birthDate = LocalDate.of(2022,9,9);
    @Builder.Default
    private Long mainAddress = null;

    public PersonDTO toPersonDTO() {
        return new PersonDTO(id,
                name,
                CPF,
                birthDate,
                mainAddress);
    }
}
