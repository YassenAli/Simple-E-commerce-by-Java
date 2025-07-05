package org.example;

import org.example.model.*;
import org.example.service.CheckoutService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // setup
        Customer yassin = new Customer("Yassin", 500);

        IProduct cheese   = new PerishableProduct("Cheese", 100, 10, LocalDate.now().plusDays(5), 0.2);
        IProduct biscuits = new PerishableProduct("Biscuits", 150, 5, LocalDate.now().plusDays(2), 0.7);
        IProduct tv       = new PhysicalProduct("TV", 2000, 2, 15);
        IProduct scratch  = new DigitalProduct("ScratchCard", 50, 20);

        scenarioSuccess(yassin, cheese, biscuits);
        // to run the following scenarios uncomment each one then run it, these scenarios output error to test other test cases that output error
//        scenarioEmptyCart(yassin);
//        scenarioOutOfStock(yassin, tv);
//        scenarioExpired(yassin);
//        scenarioInsufficientFunds(tv);
//        scenarioMultipleItems(yassin, cheese, scratch);
    }

    private static void scenarioSuccess(Customer customer, IProduct cheese, IProduct biscuits) {
        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        System.out.printf("Customer balance before payment: %.0f%n", customer.getBalance());
        try {
            new CheckoutService().checkout(customer, cart);
        } catch (Exception e) {
            System.err.println("Checkout failed: " + e.getMessage());
        }
    }

    private static void scenarioEmptyCart(Customer customer) {
        Cart cart = new Cart();
        try {
            new CheckoutService().checkout(customer, cart);
        } catch (Exception e) {
            System.err.println("Expected error (empty cart): " + e.getMessage());
        }
    }

    private static void scenarioOutOfStock(Customer customer, IProduct tv) {
        Cart cart = new Cart();
        cart.add(tv, 3); // only 2 TVs in stock
        try {
            new CheckoutService().checkout(customer, cart);
        } catch (Exception e) {
            System.err.println("Expected error (out of stock): " + e.getMessage());
        }
    }

    private static void scenarioExpired(Customer customer) {
        IProduct expired = new PerishableProduct("Old Cheese", 80, 1, LocalDate.now().minusDays(1), 0.2);
        Cart cart = new Cart();
        cart.add(expired, 1);
        try {
            new CheckoutService().checkout(customer, cart);
        } catch (Exception e) {
            System.err.println("Expected error (expired): " + e.getMessage());
        }
    }

    private static void scenarioInsufficientFunds(IProduct tv) {
        Customer ali = new Customer("Ali", 100);
        Cart cart = new Cart();
        cart.add(tv, 1);
        System.out.printf("Ali's balance before payment: %.0f%n", ali.getBalance());
        try {
            new CheckoutService().checkout(ali, cart);
        } catch (Exception e) {
            System.err.println("Expected error (insufficient funds): " + e.getMessage());
        }
    }

    private static void scenarioMultipleItems(Customer customer, IProduct cheese, IProduct scratchCard) {
        Cart cart = new Cart();
        cart.add(cheese, 1);
        cart.add(scratchCard, 2);
        System.out.printf("%s's balance before payment: %.0f%n", customer.getName(), customer.getBalance());
        try {
            new CheckoutService().checkout(customer, cart);
        } catch (Exception e) {
            System.err.println("Checkout failed: " + e.getMessage());
        }
    }
}
