package com.colak.bigdecimal;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * See <a href="https://blog.dimkus.com/java-bigdecimal-for-monetary-settlements-558766d11c55">...</a>
 */
@Slf4j
public class BigDecimalStringBasicOperationsTest {

    public static void main(String[] args) {
        // BigDecimal is not a built-in primitive type, so the usual operators like “+” and “-” do not apply.
        // Instead, you need to use the appropriate add(…), subtract(…), multiply(…) and divide(…) methods respectively.
        var a = new BigDecimal("10.000");
        var b = new BigDecimal("3.0");

        // 13.000
        log.info("add : " + a.add(b));

        // 7.000
        log.info("subtract : " + a.subtract(b));

        // 30.0000
        log.info("multiply : " + a.multiply(b));

        // When dividing, be sure to specify a rounding strategy.
        // In cases where the result of the division is an infinite fraction, BigDecimal could use up all available memory
        // for representation, since it allows you to store any precision.
        // Therefore, if no precision is explicitly specified and such a situation occurs during division,
        // BigDecimal throws an ArithmeticException.
        // 3.333
        log.info("divide : " + a.divide(b, RoundingMode.HALF_EVEN));

    }
}
