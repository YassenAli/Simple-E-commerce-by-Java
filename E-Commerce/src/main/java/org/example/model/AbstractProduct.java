package org.example.model;

public abstract class AbstractProduct implements IProduct {
    private String name;
    private double price;
    private int quantity;

    protected AbstractProduct(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override public String getName()       { return name; }
    @Override public double getPrice()      { return price; }
    @Override public int getQuantity()      { return quantity; }
    @Override public void reduceQuantity(int amount) {
        quantity -= amount;
    }
}