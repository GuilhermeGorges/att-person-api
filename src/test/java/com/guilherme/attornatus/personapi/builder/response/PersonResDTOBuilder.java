package com.guilherme.attornatus.personapi.builder.response;

import lombok.Builder;
import com.guilherme.attornatus.personapi.dto.response.AddressResDTO;
import com.guilherme.attornatus.personapi.dto.response.PersonResDTO;

import java.time.LocalDate;

@Builder
public class PersonResDTOBuilder {
    @Builder.Default
    private Long id = 1L;
    @Builder.Default
    private String name = "Larissa Abraão";
    @Builder.Default
    private String CPF = "03171313030";
    @Builder.Default
    private LocalDate birthDate = LocalDate.parse("2022-09-09");
    @Builder.Default
    private AddressResDTO mainAddress = null;

    public PersonResDTO toPersonResDTO() {
        return new PersonResDTO(id,
                name,
                CPF,
                birthDate,
                mainAddress);
    }
}
