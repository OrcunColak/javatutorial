package com.colak.jakarta.annotations.nonnull;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NonNullTest {

    public static void main(String[] args) {
        String str = null;
        try {
            String result = processString(str);
            log.error("Result : {}", result);
        } catch (Exception exception) {
            log.error("Exception from processString", exception);
        }
    }

    private static String processString(@Nonnull String input) {
        if (input == null) {
            return "Input is null";
        } else {
            return "Input is not null. It is " + input;
        }

    }
}
