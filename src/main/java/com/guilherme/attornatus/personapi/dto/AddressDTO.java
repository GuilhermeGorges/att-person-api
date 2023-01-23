package com.guilherme.attornatus.personapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;

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
}
