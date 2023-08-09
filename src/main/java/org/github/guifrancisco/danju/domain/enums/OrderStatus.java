package org.github.guifrancisco.danju.domain.enums;

public enum OrderStatus {
    PLACED("placed"),
    PROCESSING("processing"),
    DELIVERED("delivered");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
