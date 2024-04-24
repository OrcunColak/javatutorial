package com.colak.java8.optional;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
class OptionalConsumeTest {

    public static void main() {
        Optional<String> optional = getOptional("foo");
        getValue(optional);
        getValueOrThrow(optional);
        consume(optional);
    }

    private static Optional<String> getOptional(String value) {
        return Optional.ofNullable(value);
    }

    private static void getValue(Optional<String> optional) {
        String alternative = optional.orElse("alternative");
        String alternative1 = optional.orElseGet(() -> "alternative");
        log.info("Optional alternative : {}", alternative);
        log.info("Optional alternative1 : {}", alternative1);
    }

    private static void getValueOrThrow(Optional<String> optional) {
        String value = optional.orElseThrow();
        String value1 = optional.orElseThrow(() -> new RuntimeException("exception"));
        log.info("Optional value : {}", value);
        log.info("Optional value1 : {}", value1);
    }

    private static void consume(Optional<String> optional) {
        optional.ifPresent(value -> log.info("Optional value is : {}", value));
        optional.ifPresentOrElse(
                value -> log.info("Optional value is : {}", value),
                () -> log.info("no value"));
    }


}
