package org.example.exception;

/**
 * Thrown when a customer has insufficient balance.
 */
public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() {
        super("Insufficient funds.");
    }
}
