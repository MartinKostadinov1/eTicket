package com.mkostadinov.eticketbackend.exception.payment;

public class PaymentFailedException extends RuntimeException {
    public PaymentFailedException() {
        super();
    }

    public PaymentFailedException(String message) {
        super(message);
    }

    public PaymentFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentFailedException(Throwable cause) {
        super(cause);
    }
}
