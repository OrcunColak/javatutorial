package com.colak.gof.singleton.doublechecked;

// See https://levelup.gitconnected.com/optimizing-delayed-initialization-in-java-with-threads-and-functional-programming-techniques-460a3ed1d812

public class Product {
    private final String name;
    private final double basePrice;
    private volatile Double finalPrice;

    public Product(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    private double calculateFinalPrice() {
        System.out.println("Calculating final price for " + name);
        return basePrice * 1.2;
    }

    public double getFinalPrice() {
        if (finalPrice == null) { // First check without locking
            synchronized (this) {
                if (finalPrice == null) { // Second check with locking
                    finalPrice = calculateFinalPrice();
                }
            }
        }
        return finalPrice;
    }
}
