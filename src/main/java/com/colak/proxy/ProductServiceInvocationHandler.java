package com.colak.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class ProductServiceInvocationHandler implements InvocationHandler {
    private final ProductService productService;

    public ProductServiceInvocationHandler(ProductService productService) {
        this.productService = productService;
    }

    // Log method execution
    // Throw exception if price is < 0
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("set")) {
            double newPrice = (double) args[0];
            if (newPrice < 0.0) {
                throw new RuntimeException("Price cannot be negative.");
            }
        }

        Object returnValue = method.invoke(productService, args);

        log.info("Method executed: " + method.getName() + " for product: " + productService.getName());
        return returnValue;
    }
}
