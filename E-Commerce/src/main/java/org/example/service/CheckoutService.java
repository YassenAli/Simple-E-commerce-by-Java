package org.example.service;

import org.example.model.*;
import org.example.exception.*;

import java.util.ArrayList;
import java.util.List;

public class CheckoutService {
    private static  double SHIPPING_FEE = 30;

    public void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) throw new EmptyCartException();

        System.out.printf("Balance before: %.0f\n", customer.getBalance());
        double subtotal = 0;
        List<IShippable> toShip = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            IProduct p = item.getProduct();
            int qty   = item.getQuantity();

            if (p instanceof IExpirable e && e.isExpired())
                throw new ExpiredProductException(p.getName());

            if (qty > p.getQuantity())
                throw new OutOfStockException(p.getName());

            subtotal += item.getTotalPrice();
            p.reduceQuantity(qty);

            if (p instanceof IShippable s) {
                for (int i=0; i<qty; i++) toShip.add(s);
            }
        }

        double shipFee = toShip.isEmpty()?0:SHIPPING_FEE;
        if (!toShip.isEmpty()) ShippingService.ship(toShip);

        double total = subtotal + shipFee;
        if (customer.getBalance() < total)
            throw new InsufficientFundsException();

        customer.debit(total);

        // Receipt
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %-15s %.0f\n", item.getQuantity(), item.getProduct().getName(), item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal         %.0f\n", subtotal);
        System.out.printf("Shipping         %.0f\n", shipFee);
        System.out.printf("Amount           %.0f\n", total);
        System.out.printf("Balance left     %.0f\n", customer.getBalance());
    }
}
