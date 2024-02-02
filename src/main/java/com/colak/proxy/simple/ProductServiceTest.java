package com.colak.proxy.simple;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;


@Slf4j
public class ProductServiceTest {

    // Original code is from
    // https://blog.stackademic.com/aop-and-proxy-design-pattern-in-spring-boot-d0e360783651
    public static void main(String[] args) {
        ProductService productServiceImpl = new ProductServiceImpl();
        productServiceImpl.setName("Laptop");
        productServiceImpl.setPrice(1000.0);

        // Returns a proxy instance for the specified interfaces
        // that dispatches method invocations to the specified invocation handler.
        ProductServiceInvocationHandler productHandler = new ProductServiceInvocationHandler(productServiceImpl);
        ProductService productProxy = (ProductService) Proxy
                .newProxyInstance(
                        ProductService.class.getClassLoader(),
                        new Class[]{ProductService.class},
                        productHandler
                );

        productProxy.setPrice(1200.0);
        log.info("Proxy Product after price change: " + productProxy.getName() + " - " + productProxy.getPrice());

        try {
            productProxy.setPrice(-1);
        } catch (Exception exception) {
            log.error("Exception ", exception);
        }
    }
}
