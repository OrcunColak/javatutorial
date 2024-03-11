package com.colak.bigdecimal;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * See <a href="https://docs.openrewrite.org/recipes/staticanalysis/bigdecimaldoubleconstructorrecipe">...</a>
 */
@Slf4j
public class BigDecimalDoubleConstructorTest {

    public static void main(String[] args) {

        // Use of new BigDecimal(double) constructor can lead to loss of precision.
        // Use BigDecimal.valueOf(double) instead.
        // For example writing new BigDecimal(0.1) does not create a BigDecimal which is exactly equal to 0.1,
        // but it is equal to 0.1000000000000000055511151231257827021181583404541015625.
        // This is because 0.1 cannot be represented exactly as a double (or, for that matter, as a binary fraction of any finite length).
        BigDecimal bigDecimal1 = BigDecimal.valueOf(0.1);
        BigDecimal bigDecimal2 = new BigDecimal(0.1);

        log.info("bigDecimal1  {}", bigDecimal1);
        log.info("bigDecimal2  {}", bigDecimal2);
        log.info("bigDecimal1.equals(bigDecimal2) : {}", bigDecimal1.equals(bigDecimal2));

    }
}
