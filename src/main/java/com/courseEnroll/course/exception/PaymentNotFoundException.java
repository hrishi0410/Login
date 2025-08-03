package com.courseEnroll.course.exception;

public class PaymentNotFoundException {

    public class PaymentProcessingException extends RuntimeException {
        public PaymentProcessingException(String message) {
            super(message);
        }
    }
}
