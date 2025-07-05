package org.example.service;

import org.example.model.Shippable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Aggregates and prints shipping details.
 */
public class ShippingService {
    public static void ship(List<Shippable> items) {
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        Map<String, Integer> countMap = new LinkedHashMap<>();
        Map<String, Double> weightMap = new LinkedHashMap<>();

        // Count items and sum weights
        for (Shippable s : items) {
            countMap.merge(s.getName(), 1, Integer::sum);
            weightMap.merge(s.getName(), s.getWeight(), Double::sum);
            totalWeight += s.getWeight();
        }

        // Print each with correct qty and total weight
        for (String name : countMap.keySet()) {
            int qty = countMap.get(name);
            double w = weightMap.get(name);
            System.out.printf("%dx %-15s %.0fg%n", qty, name, w * 1000);
        }

        System.out.printf("Total package weight %.1fkg%n%n", totalWeight);
    }
}
