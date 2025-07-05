package org.example.model;

/**
 * Contract for any item that can be shipped.
 */
public interface Shippable {
    String getName();
    double getWeight();
}