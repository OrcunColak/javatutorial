package com.colak.gof.singleton.doublechecked;

import java.util.function.Supplier;

// See https://levelup.gitconnected.com/optimizing-delayed-initialization-in-java-with-threads-and-functional-programming-techniques-460a3ed1d812

public class ProductWithSupplier {
    private final String name;
    private final double basePrice;

    // Supplier for lazy initialization with memoization
    private Supplier<Double> finalPriceSupplier = this::lazyFinalPrice;

    public ProductWithSupplier(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    // Method to calculate the final price (e.g., applying taxes, discounts)
    private double calculateFinalPrice() {
        System.out.println("Calculating final price for " + name);
        return basePrice * 1.2; // Example: 20% tax
    }

    // Lazily compute and cache the final price
    private double lazyFinalPrice() {
        double result = calculateFinalPrice();
        // Memoize the result by replacing the Supplier with a constant value
        finalPriceSupplier = () -> result;
        return result;
    }

    // Public method to get the final price (lazily computed on first call)
    public double getFinalPrice() {
        return finalPriceSupplier.get();
    }
}
