package com.colak.gof.builder.staticinnerclass;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ProductTest {

    public static void main(String[] args) {
        buildWithDefaultValue();
    }

    private static void buildWithDefaultValue() {
        Product product = Product.builder()
                .withPart1("hey")
                .build();
        log.info(product.getPart1());
        log.info(product.getPart2());
    }
}
