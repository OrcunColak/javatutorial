package com.colak.bigdecimal;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * See <a href="https://blog.dimkus.com/java-bigdecimal-for-monetary-settlements-558766d11c55">...</a>
 */
@Slf4j
public class BigDecimalStringConstructorTest {

    // Initializing with integers does not allow you to specify fractional values.
    // Therefore, the universal way to initialize BigDecimal is exactly with a String.
    // Then you will get exactly the number and with the number of digits you specify.
    public static void main(String[] args) {
        // Conversion to String
        // To get a textual representation of an object, it is common in Java to use the toString() method.
        // In BigDecimal, however, it is better to use the toPlainString() method for this purpose.
        // In most cases, the text representation returned by these methods is the same. But there are some differences!
        //  If the value is very small and contains a large number of leading zeros in the fractional part, toString()
        //  returns an “engineered” representation of the number “1E-10”, which means 10 to the power of -10.
        //  This format can break the logic of the interface. Always use toPlainString() to avoid this situation.

        // 10.000000000000000000000000000000000000
        BigDecimal bigDecimal = new BigDecimal(10)
                .setScale(36, RoundingMode.HALF_EVEN);
        log.info(bigDecimal.toPlainString());


        // 10.109999999999999431565811391919851303
        BigDecimal bigDecimal1 = new BigDecimal(10.11)
                .setScale(36, RoundingMode.HALF_EVEN);
        log.info(bigDecimal1.setScale(36, RoundingMode.HALF_EVEN).toPlainString());

        // 10.110000000000000000000000000000000000
        BigDecimal bigDecimal2 = new BigDecimal("10.11")
                .setScale(36, RoundingMode.HALF_EVEN);
        log.info(bigDecimal2.toPlainString());
    }
}
