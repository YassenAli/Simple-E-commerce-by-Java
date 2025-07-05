package org.example.model;

import java.time.LocalDate;

public class PerishableProduct extends AbstractProduct implements IShippable, IExpirable {
    private LocalDate expirationDate;
    private double weight;
    public PerishableProduct(String name, double price, int quantity,
                             LocalDate expirationDate, double weight) {
        super(name, price, quantity);
        this.expirationDate = expirationDate;
        this.weight = weight;
    }
    @Override public double getWeight() { return weight; }
    @Override public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }
}
