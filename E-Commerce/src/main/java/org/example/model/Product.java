package org.example.model;

import java.time.LocalDate;

/**
 * Represents a product with optional expiry and shipping weight.
 */
public class Product implements Shippable {
    private String name;
    private double price;
    private int quantity;
    private LocalDate expirationDate;
    private double weight;

    public Product(String name, double price, int quantity, LocalDate expirationDate, double weight) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int amount) {
        this.quantity -= amount;
    }

    public boolean isExpired() {
        return expirationDate != null && LocalDate.now().isAfter(expirationDate);
    }

    public boolean isShippable() {
        return weight > 0;
    }
}
