package com.guilherme.attornatus.personapi.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guilherme.attornatus.personapi.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    @NotNull
    private Long id;
    @Max(200)
    @NotNull
    private String name;
    @Max(20)
    @NotNull
    private String CPF;
    @NotNull
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthDate;
    @Valid
    private AddressDTO mainAddress;
}
