package com.colak.bigdecimal;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * See <a href="https://blog.dimkus.com/java-bigdecimal-for-monetary-settlements-558766d11c55">...</a>
 */
@Slf4j
public class BigDecimalParseTest {

    public static void main(String[] args) {
        log.info(parseAmount("1 234 456,78").toPlainString());
        log.info(parseAmount("1.234.456,78").toPlainString());
        log.info(parseAmount("1 234.456,78").toPlainString());
        log.info(parseAmount("1.234").toPlainString());
    }

    // BigDecimal expects a value from digits and a dot as a divisor,
    public static BigDecimal parseAmount(String amount) throws NumberFormatException {
        if (amount == null || amount.isBlank()) {
            return null; // or throw an exception
        }
        var buf = new StringBuilder();
        var isCommaPresent = false;

        var source = amount.strip().toCharArray();
        if (source[0] == '-' || source[0] == '+') {
            buf.append(source[0]);
        }

        for (int i = buf.length(); i < source.length; i++) {
            if (Character.isDigit(source[i])) {
                buf.append(source[i]);
            } else if (source[i] == ',') {
                if (isCommaPresent) {
                    // e.g should be 1.234.456,78
                    throw new NumberFormatException("More then one comma in amount: " + amount);
                }
                buf.append('.');
                isCommaPresent = true;
            } else if (source[i] == '.' || Character.isWhitespace(source[i])) {
                // skip it
            } else {
                throw new NumberFormatException("Invalid character in amount: " + source[i]);
            }
        }
        return new BigDecimal(buf.toString());
    }
}
