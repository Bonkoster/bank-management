package com.bonkost.bankmanagement.exception;

/**
 * @author Vladimir Luchnikov
 */

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
