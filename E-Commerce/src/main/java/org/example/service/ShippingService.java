package org.example.service;

import org.example.model.IShippable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Aggregates and prints shipping details.
 */
public class ShippingService {
    public static void ship(List<IShippable> items) {
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        Map<String,Integer> count = new LinkedHashMap<>();
        Map<String,Double> weight = new LinkedHashMap<>();
        for (IShippable s: items) {
            String name = s.getName();
            count.merge(name, 1, Integer::sum);
            weight.merge(name, s.getWeight(), Double::sum);
            totalWeight += s.getWeight();
        }
        for (String name: count.keySet()) {
            System.out.printf("%dx %-15s %.0fg\n", count.get(name), name, weight.get(name)*1000);
        }
        System.out.printf("Total package weight %.1fkg\n\n", totalWeight);
    }
}

