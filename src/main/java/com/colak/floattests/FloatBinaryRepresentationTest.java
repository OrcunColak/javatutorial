package com.colak.floattests;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

// See https://dzone.com/articles/understanding-floating-points-number-in-java
@Slf4j
@UtilityClass
class FloatBinaryRepresentationTest {

    public static void main() {
        float num = 12.375f;  // Example float number

        // Convert the float to an integer bit representation using Float.floatToIntBits
        int intBits = Float.floatToIntBits(num);

        // Extract the sign (1 bit)
        int sign = (intBits >> 31) & 1;

        // Extract the exponent (8 bits)
        int exponent = (intBits >> 23) & 0xFF;

        // Extract the mantissa (23 bits)
        int mantissa = intBits & 0x7FFFFF;

        // Print the results
        log.info("Float: {}", num);
        String binaryString = String.format("%32s", Integer.toBinaryString(intBits)).replace(' ', '0');
        log.info("Binary Representation: {}", binaryString);
        log.info("Sign Bit: {}", sign);
        log.info("Exponent: {} (Unbiased: {})", Integer.toBinaryString(exponent), (exponent - 127));
        log.info("Mantissa: {}", Integer.toBinaryString(mantissa));
    }
}
