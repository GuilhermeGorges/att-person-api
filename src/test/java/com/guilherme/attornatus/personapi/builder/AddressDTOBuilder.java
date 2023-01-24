package com.guilherme.attornatus.personapi.builder;

import com.guilherme.attornatus.personapi.dto.AddressDTO;
import com.guilherme.attornatus.personapi.entity.Person;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public class AddressDTOBuilder {
    @Builder.Default
    private Long id = 1L;
    @Builder.Default
    private String logradouro = "Rua Santa Cecilia - Itoupazinha";
    @Builder.Default
    private String CEP = "89012000";
    @Builder.Default
    private Long number = 190L;
    @Builder.Default
    private String city = "Blumenau - SC";
    @Builder.Default
    private Person person = new Person(
            1L,
            "Larissa Abraão",
            LocalDate.of(2022,9,9),
            "03171313030",
            null);

    public AddressDTO toAddressDTO() {
        return new AddressDTO(id,
                logradouro,
                CEP,
                number,
                city,
                person);
    }
}
