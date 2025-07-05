package org.example.service;
import org.example.model.Product;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Manages items a customer intends to purchase.
 */
public class Cart {
    private Map<Product, Integer> items = new LinkedHashMap<>();

    public void add(Product p, int qty) {
        if (qty <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        int current = items.getOrDefault(p, 0);
        if (current + qty > p.getQuantity()) {
            throw new IllegalArgumentException(p.getName() + ": cannot add more than stock");
        }
        items.put(p, current + qty);
    }

    public Map<Product, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
