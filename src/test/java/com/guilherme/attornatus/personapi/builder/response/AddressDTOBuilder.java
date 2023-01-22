package com.guilherme.attornatus.personapi.builder.response;

import com.guilherme.attornatus.personapi.dto.response.AddressDTO;
import lombok.Builder;

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

    public AddressDTO toAddressDTO() {
        return new AddressDTO(id,
                logradouro,
                CEP,
                number,
                city);
    }
}
