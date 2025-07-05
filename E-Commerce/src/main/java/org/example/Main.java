package org.example;

import org.example.model.Customer;
import org.example.model.ProductFactory;
import org.example.model.Product;
import org.example.service.Cart;
import org.example.service.Ecommerce;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // setup
        Customer yassin = new Customer("Yassin", 500);
        Product cheese   = ProductFactory.createPerishable("Cheese", 100, 10,
                LocalDate.now().plusDays(5), 0.2);
        Product biscuits = ProductFactory.createPerishable("Biscuits", 150, 5,
                LocalDate.now().plusDays(2), 0.7);
        Product tv       = ProductFactory.createPerishable("TV", 2000, 2,
                null, 15);
        Product scratch  = ProductFactory.createDigital("ScratchCard", 50, 20);
        scenarioSuccess(yassin, cheese, biscuits);
        // to run the following scenarios uncomment each one then run it, these scenarios output error to test other test cases that output error
//        scenarioEmptyCart(yassin);
//        scenarioOutOfStock(yassin, tv);
//        scenarioExpired(yassin);
//        scenarioInsufficientFunds(tv);
    }

    private static void scenarioSuccess(Customer yassin, Product cheese, Product biscuits) {
        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        System.out.printf("Customer balance before payment: %.0f%n", yassin.getBalance());
        try {
            Ecommerce.checkout(yassin, cart);
        } catch (Exception e) {
            System.err.println("Checkout failed: " + e.getMessage());
        }
    }

    private static void scenarioEmptyCart(Customer yassin) {
        Cart cart = new Cart();
        try {
            Ecommerce.checkout(yassin, cart);
        } catch (Exception e) {
            System.err.println("Expected error (empty cart): " + e.getMessage());
        }
    }

    private static void scenarioOutOfStock(Customer yassin, Product tv) {
        Cart cart = new Cart();
        cart.add(tv, 3); // only 2 TVs in stock
        try {
            Ecommerce.checkout(yassin, cart);
        } catch (Exception e) {
            System.err.println("Expected error (out of stock): " + e.getMessage());
        }
    }

    private static void scenarioExpired(Customer yassin) {
        Product expired = new Product("Old Cheese", 80, 1, LocalDate.now().minusDays(1), 0.2);
        Cart cart = new Cart();
        cart.add(expired, 1);
        try {
            Ecommerce.checkout(yassin, cart);
        } catch (Exception e) {
            System.err.println("Expected error (expired): " + e.getMessage());
        }
    }

    private static void scenarioInsufficientFunds(Product tv) {
        Customer Ali = new Customer("Ali", 100);
        Cart cart = new Cart();
        cart.add(tv, 1);
        System.out.printf("Ali's balance before payment: %.0f%n", Ali.getBalance());
        try {
            Ecommerce.checkout(Ali, cart);
        } catch (Exception e) {
            System.err.println("Expected error (insufficient funds): " + e.getMessage());
        }
    }
}
