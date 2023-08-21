package org.github.guifrancisco.danju.domain.enums;

import lombok.Getter;

@Getter
public enum PaymentType {

    CREDIT_CARD("credit card"),
    DEBIT_CARD("debit card"),
    PAYMENT_SLIP("payment slip"),
    PAYPAL("paypal"),
    PIX("pix");

    private final String paymentType;

    PaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

}
