package com.colak.algorithms.getfirst;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * See <a href="https://medium.com/@renanschmitt/java-class-to-get-the-first-result-from-multiple-sources-deeb5f862d83">...</a>
 */
@Slf4j
public class GetFirstExecutorTest {

    public static void main(String[] args) {
        orElseGet();
        orElseThrow();
    }

    private static void orElseThrow() {
        String result = GetFirstExecutor
                .get(GetFirstExecutorTest::result1)
                .or(GetFirstExecutorTest::result2)
                .value()
                .orElseThrow(RuntimeException::new); // orElseThrow from Optional
        log.info("Result is : {}", result);
    }

    private static void orElseGet() {
        String result = GetFirstExecutor
                .<String>get(Optional::empty)
                .or(Optional::empty)
                .orElseGet(() -> "default value");

        log.info("Result is : {}", result);
    }

    private static Optional<String> result1() {
        return Optional.empty();
    }

    private static Optional<String> result2() {
        return Optional.empty();
    }
}
