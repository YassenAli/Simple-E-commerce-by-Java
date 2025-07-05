package org.example.exception;

/**
 * Thrown when a product is expired.
 */
public class ExpiredProductException extends RuntimeException {
    public ExpiredProductException(String productName) {
        super(productName + " is expired.");
    }
}
