package com.colak.bigdecimal;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * See <a href="https://blog.dimkus.com/java-bigdecimal-for-monetary-settlements-558766d11c55">...</a>
 */
@Slf4j
public class BigDecimalCompareToTest {

    // In Java, it is common to compare objects for equality using the equals(...) method.
    // However, in the case of BigDecimal, you should not do this because equals(...) does not take precision into account.
    // The numbers 100 and 100.0 are not equal.
    // Instead, use the compareTo(...) method, which returns 0 on equality.
    // If a > b, the method will return 1.
    // If a < b, the method returns -1.
    public static void main(String[] args) {
        var a = new BigDecimal("100");
        var b = new BigDecimal("100.0");

        // false
        log.info("Equals : " + a.equals(b));

        // true
        log.info("compareTo : " + (a.compareTo(b) == 0));
    }
}
