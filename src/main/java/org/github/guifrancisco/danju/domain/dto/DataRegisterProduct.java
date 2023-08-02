package org.github.guifrancisco.danju.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterProduct(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotNull
        double price
) {
}
