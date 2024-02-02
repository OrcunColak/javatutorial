package com.colak.proxy.simple;

public class ProductServiceImpl implements ProductService {

    private String name;
    private double price;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }
}
