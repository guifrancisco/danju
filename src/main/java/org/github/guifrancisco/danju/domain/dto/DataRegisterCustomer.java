package org.github.guifrancisco.danju.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record DataRegisterCustomer(
        @NotBlank
        String name,
        @NotBlank
        String telephone,
        @NotBlank
        String address
) {
}
