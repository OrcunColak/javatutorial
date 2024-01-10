package com.colak.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

@Slf4j
public class ProductServiceImpl implements ProductService {

    private String name;
    private double price;

    // Original code is from
    // https://blog.stackademic.com/aop-and-proxy-design-pattern-in-spring-boot-d0e360783651
    public static void main(String[] args) {
        ProductService productService = new ProductServiceImpl();
        productService.setName("Laptop");
        productService.setPrice(1000.0);

        ProductServiceInvocationHandler productHandler = new ProductServiceInvocationHandler(productService);
        ProductService productProxy = (ProductService) Proxy.newProxyInstance(ProductService.class.getClassLoader(),
                new Class[]{ProductService.class}, productHandler);

        productProxy.setPrice(1200.0);
        log.info("Proxy Product after price change: " + productProxy.getName() + " - " + productProxy.getPrice());

        try {
            productProxy.setPrice(-1);
        } catch (Exception exception) {
            log.error("Exception ", exception);
        }
    }

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
