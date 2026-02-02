package com.colak.java22;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Gatherers;
import java.util.stream.Stream;

@Slf4j
public class StreamGathererMapTest {

    static void main() {
        var doubled = Stream.of(1, 2, 3, 4)
                .gather(Gatherers.mapConcurrent(4,x -> x * 2))
                .toList();
        log.info("Result {}", doubled);
    }
}
