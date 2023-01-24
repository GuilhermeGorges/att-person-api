package com.guilherme.attornatus.personapi.dto;

import com.guilherme.attornatus.personapi.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private Long id;
    private String logradouro;
    private String CEP;
    private Long number;
    private String city;
    private Person person;
}
