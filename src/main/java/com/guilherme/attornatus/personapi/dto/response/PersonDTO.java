package com.guilherme.attornatus.personapi.dto.response;

import com.guilherme.attornatus.personapi.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private Long id;
    private String name;
    private Long CPF;
    private LocalDate birthDate;
    private Address mainAddress;
    private List<Address> addresses;
}
