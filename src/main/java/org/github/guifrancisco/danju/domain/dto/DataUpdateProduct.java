package org.github.guifrancisco.danju.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataUpdateProduct(

        String name,

        String description,

        double price
) {
}
