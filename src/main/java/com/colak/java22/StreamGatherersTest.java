package com.colak.java22;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Gatherers;

/**
 * See <a href="https://levelup.gitconnected.com/java-22-explaining-new-features-with-examples-7fb2571188c8">...</a>
 */
@Slf4j
public class StreamGatherersTest {
    public static void main(String[] args) {
        fixedSize();
        slidingWindow();
    }

    private static void slidingWindow() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        List<List<Integer>> slidingWindows = numbers.stream()
                .gather(Gatherers.windowSliding(3))
                .toList();
        // [[1, 2, 3], [2, 3, 4], [3, 4, 5]]
        log.info(STR."SlidingWindow\{slidingWindows}");
    }

    private static void fixedSize() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        List<List<Integer>> fixedWindows = numbers.stream()
                .gather(Gatherers.windowFixed(3))
                .toList();

        // [[0, 1, 2], [3, 4, 5]]
        log.info(STR."fixedWindow\{fixedWindows}");
    }


}
