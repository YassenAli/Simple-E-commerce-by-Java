package org.example.model;

import java.time.LocalDate;

/**
 * Factory for creating different types of products.
 */
public class ProductFactory {
    public static Product createPerishable(String name, double price,
                                           int qty, LocalDate exp, double weight) {
        return new Product(name, price, qty, exp, weight);
    }

    public static Product createDigital(String name, double price, int qty) {
        return new Product(name, price, qty, null, 0);
    }
}
