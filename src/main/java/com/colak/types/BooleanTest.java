package com.colak.types;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
class BooleanTest {

    public static void main() {
        testParseBoolean();
    }

    private static void testParseBoolean() {
        // Boolean.parseBoolean is case-insensitive
        log.info("Insensitive : {}", Boolean.parseBoolean("tRue"));

    }
}
