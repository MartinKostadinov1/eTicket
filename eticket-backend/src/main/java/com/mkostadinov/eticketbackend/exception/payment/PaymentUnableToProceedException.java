package com.mkostadinov.eticketbackend.exception.payment;

public class PaymentUnableToProceedException extends RuntimeException {

    public PaymentUnableToProceedException() {
        super();
    }

    public PaymentUnableToProceedException(String message) {
        super(message);
    }

    public PaymentUnableToProceedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentUnableToProceedException(Throwable cause) {
        super(cause);
    }
}
