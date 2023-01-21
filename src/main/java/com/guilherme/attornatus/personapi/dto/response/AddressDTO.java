package com.guilherme.attornatus.personapi.dto.response;

import com.guilherme.attornatus.personapi.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    @NotNull
    private Long id;
    @Max(250)
    @NotNull
    private String logradouro;
    @Max(20)
    @NotNull
    private String CEP;
    @NotNull
    private Long number;
    @Max(50)
    @NotNull
    private Long city;
    @NotNull
    @Valid
    private Person person;
}
