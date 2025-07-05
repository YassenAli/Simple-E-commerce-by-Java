package org.example.exception;

/**
 * Thrown when desired quantity exceeds available stock.
 */
public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String productName) {
        super(productName + " is out of stock.");
    }
}
