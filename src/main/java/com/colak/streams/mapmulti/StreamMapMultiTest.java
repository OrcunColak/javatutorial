package com.colak.streams.mapmulti;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
class StreamMapMultiTest {

    public static void main() {

        List<String> strings = List.of("a", "b", "c");

        List<String> result = strings.stream()
                .<String>mapMulti((element, consumer) -> {
                    consumer.accept(element.toUpperCase());
                    consumer.accept(element.toLowerCase());
                })
                .toList();

        // List : [A, a, B, b, C, c]
        log.info("List : {}", result);
    }
}


