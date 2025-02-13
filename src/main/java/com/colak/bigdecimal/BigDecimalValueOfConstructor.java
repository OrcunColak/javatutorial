package com.colak.bigdecimal;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * See https://blog.stackademic.com/these-7-pitfalls-99-of-java-developers-will-encounter-dca0134e3fb2
 */
@Slf4j
public class BigDecimalValueOfConstructor {

  
   public static void main(String[] args) {
     // Using the BigDecimal.valueOf method to initialize BigDecimal values also ensures no precision loss. 
     // The latest version of the Alibaba Java Development Manual also recommends this approach for creating BigDecimal objects.
     BigDecimal amount1 = BigDecimal.valueOf(0.02);
     BigDecimal amount2 = BigDecimal.valueOf(0.03);
     System.out.println(amount2.subtract(amount1));
   }
}
