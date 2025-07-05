package org.example.service;

import org.example.exception.*;
import org.example.model.Shippable;
import org.example.model.Product;
import org.example.model.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the checkout flow: validation, payment, and shipping.
 */
public class Ecommerce {
    /**
     * Perform checkout, throwing domain exceptions on failure.
     */
    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new EmptyCartException();
        }

        double subtotal = 0;
        List<Shippable> toShip = new ArrayList<>();

        for (var entry : cart.getItems().entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();

            if (p.isExpired()) {
                throw new ExpiredProductException(p.getName());
            }
            if (qty > p.getQuantity()) {
                throw new OutOfStockException(p.getName());
            }

            subtotal += p.getPrice() * qty;
            p.reduceQuantity(qty);
            if (p.isShippable()) {
                for (int i = 0; i < qty; i++) {
                    toShip.add(p);
                }
            }
        }

        double shippingFee = toShip.isEmpty() ? 0 : 30;
        double total = subtotal + shippingFee;
        if (customer.getBalance() < total) {
            throw new InsufficientFundsException();
        }

        customer.debit(total);
        if (!toShip.isEmpty()) {
            ShippingService.ship(toShip);
        }

        System.out.println("** Checkout receipt **");
        cart.getItems().forEach((p, q) ->
                System.out.printf("%dx %-15s %.0f%n", q, p.getName(), p.getPrice() * q)
        );
        System.out.println("----------------------");
        System.out.printf("Subtotal         %.0f%n", subtotal);
        System.out.printf("Shipping         %.0f%n", shippingFee);
        System.out.printf("Amount           %.0f%n", total);
        System.out.printf("Balance left     %.0f%n", customer.getBalance());
    }
}
