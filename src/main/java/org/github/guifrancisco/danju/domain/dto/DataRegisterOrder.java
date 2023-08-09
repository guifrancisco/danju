package org.github.guifrancisco.danju.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.time.LocalDate;

public record DataRegisterOrder(
        @NotBlank
        String customerId,
        @NotNull
        LocalDate deliveryDate,
        double totalValue,
        @NotBlank
        String status,
        @NotEmpty
        List<DataRegisterOrderLine> orderLines



) {
}
