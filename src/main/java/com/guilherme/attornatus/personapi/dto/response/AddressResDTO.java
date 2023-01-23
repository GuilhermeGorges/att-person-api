package com.guilherme.attornatus.personapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResDTO {
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
    private String city;
}
