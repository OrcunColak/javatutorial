package com.colak.streams.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Stream;

// See https://medium.com/codimis/exception-handling-in-java-streams-3f877f691595?source=explore---------9-83--------------------bec459a2_4a54_4473_bccc_cdfeef24264a-------15
@Slf4j
public class IntermediateOperationExceptionTest {

    public static void main(String[] args) {
        intermediateOperationThrowsExceptionTest();
    }

    // Catch exception out of stream
    private static void intermediateOperationThrowsExceptionTest() {
        try {
            Stream<String> stream = Stream.of("a", "b", "c");
            stream.filter(ignored -> {
                        throw new RuntimeException("Exception from filter");
                    })
                    .forEach(s -> log.info("Item : {}", s));
        } catch (RuntimeException exception) {
            log.error("Exception", exception);
        }
    }
}
