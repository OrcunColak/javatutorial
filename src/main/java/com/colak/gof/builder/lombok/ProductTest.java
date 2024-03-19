package com.colak.gof.builder.lombok;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ProductTest {
    public static void main(String[] args) {
        Product product = Product.builder()
                .name("Laptop")
                .price(999.99)
                .category("Electronics")
                .build();
        log.info(product.toString());
    }
}
