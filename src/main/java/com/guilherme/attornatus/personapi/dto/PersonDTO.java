package com.guilherme.attornatus.personapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guilherme.attornatus.personapi.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private Long id;
    private String name;
    private String CPF;
    private LocalDate birthDate;
    private Long mainAddress;
}
