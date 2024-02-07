package com.colak.streams.parallelstream;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * See <a href="https://medium.com/@JavaFusion/parallel-streaming-in-java-8-2885373a576a">...</a>
 * See <a href="https://www.baeldung.com/java-parallelstream-vs-stream-parallel">...</a>
 */
@Slf4j
public class ParallelStreamTest {

    public static void main(String[] args) {
        log.info("***** Example of stream parallel() ********");
        parallelStream();

        log.info("\n\n***** Example of stream parallelStream() ********");
        parallel();
    }

    private static void parallel() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        // Always tries to return a parallel version of the stream provided to it.
        list.stream().parallel()
                .map(i -> i * 10)
                .forEach(n -> log.info("Value ->" + n + " Thread name ->" + Thread.currentThread().getName()));

    }

    private static void parallelStream() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        // Create a stream from collection itself
        // This operation returns a "possibly parallel stream" with the collection offered as its source.
        // If the collection overrides Spliterator<E> spliterator() method, we may get different behavior
        list.parallelStream()
                .map(i -> i * 10)
                .forEach(n -> log.info("Value ->" + n + " Thread name ->" + Thread.currentThread().getName()));
    }
}
