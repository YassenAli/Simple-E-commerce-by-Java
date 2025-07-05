package org.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();
    public void add(IProduct p, int qty) {
        if (qty <= 0) throw new IllegalArgumentException("Quantity must be positive");
        if (qty > p.getQuantity()) throw new IllegalArgumentException(p.getName()+" out of stock");
        items.add(new CartItem(p, qty));
    }
    public List<CartItem> getItems() { return Collections.unmodifiableList(items); }
    public boolean isEmpty() { return items.isEmpty(); }
}
