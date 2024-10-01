package com.colak.java22;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Gatherers;


// See https://levelup.gitconnected.com/java-22-explaining-new-features-with-examples-7fb2571188c8"
@Slf4j
class StreamGatherersTest {

    public static void main() {
        fold();
        mapConcurrent();
        fixedSize();
        slidingWindow();
    }

    // fold is a stateful many-to-one gatherer which constructs an aggregate incrementally and emits that aggregate when no more input elements exist.
    private static void fold() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        Integer fold = numbers.stream()
                .gather(Gatherers.fold(() -> 0, Integer::sum))
                .findFirst()
                .orElseThrow();
        // 15
        log.info("Fold {}", fold);
    }

    // mapConcurrent is a stateful one-to-one gatherer which invokes a supplied function for each input element concurrently, up to a supplied limit.
    private static void mapConcurrent() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        List<Integer> mapConcurrent = numbers.stream()
                .gather(Gatherers.mapConcurrent(4, a -> a * 2))
                .toList();
        //[2, 4, 6, 8, 10]
        log.info("MapConcurrent {}", mapConcurrent);
    }


    // windowSliding is a stateful many-to-many gatherer which groups input elements into lists of a supplied size.
    // After the first window, each subsequent window is created from a copy of its predecessor by dropping the first
    // element and appending the next element from the input stream.
    private static void slidingWindow() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        List<List<Integer>> slidingWindows = numbers.stream()
                .gather(Gatherers.windowSliding(3))
                .toList();
        // [[1, 2, 3], [2, 3, 4], [3, 4, 5]]
        log.info("SlidingWindow {}", slidingWindows);
    }

    // windowFixed is a stateful many-to-many gatherer which groups input elements into lists of a supplied size,
    // emitting the windows downstream when they are full.
    private static void fixedSize() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        List<List<Integer>> fixedWindows = numbers.stream()
                .gather(Gatherers.windowFixed(3))
                .toList();

        // [[0, 1, 2], [3, 4, 5]]
        log.info("fixedWindow {}", fixedWindows);
    }
}
