package com.colak.string.split;

import lombok.extern.slf4j.Slf4j;

// See https://medium.com/@AlexanderObregon/javas-string-split-method-explained-77bdaddaae79
@Slf4j
public class StringSplitTest {

    public static void main() {
        emptyTokens();
        skipEmptyTokens();
        limitTokens();
    }

    private static void emptyTokens() {
        String text = "cat,,dog,,bird";
        String[] animals = text.split(",", -1);

        for (String animal : animals) {
            log.info(animal);
        }
    }

    private static void skipEmptyTokens() {
        String text = "cat,,dog,,bird";
        String[] animals = text.split(",", -1);

        for (String animal : animals) {
            if (!animal.isEmpty()) {
                log.info(animal);
            }
        }
    }

    // You can also limit the number of substrings returned by the split() method.
    // This is useful when you only need a specific number of parts from the original string.
    // Notice that the last element contains the remainder of the original string.
    private static void limitTokens() {
        String sentence = "first-second-third-fourth";
        String[] parts = sentence.split("-", 3);

        for (String part : parts) {
            log.info(part);
        }
    }
}
