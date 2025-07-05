package org.example.model;

public interface IProduct {
    String getName();
    double getPrice();
    int getQuantity();
    void reduceQuantity(int amt);
}
