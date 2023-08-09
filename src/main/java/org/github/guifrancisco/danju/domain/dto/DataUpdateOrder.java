package org.github.guifrancisco.danju.domain.dto;

import java.time.LocalDate;

public record DataUpdateOrder(

        LocalDate deliveryDate,
        String paymentType,
        String status

) {
}
