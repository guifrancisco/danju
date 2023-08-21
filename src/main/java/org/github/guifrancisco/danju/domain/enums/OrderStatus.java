package org.github.guifrancisco.danju.domain.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PLACED("placed"),
    PROCESSING("processing"),
    DELIVERED("delivered");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

}
