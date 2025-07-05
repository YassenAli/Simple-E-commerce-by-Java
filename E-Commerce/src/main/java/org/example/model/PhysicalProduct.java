package org.example.model;

public class PhysicalProduct extends AbstractProduct implements IShippable {
    private double weight;
    public PhysicalProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }
    @Override public double getWeight() { return weight; }
}
