package org.example.exception;

/**
 * Thrown when attempting to checkout with an empty cart.
 */
public class EmptyCartException extends RuntimeException {
    public EmptyCartException() {
        super("Cart is empty.");
    }
}
