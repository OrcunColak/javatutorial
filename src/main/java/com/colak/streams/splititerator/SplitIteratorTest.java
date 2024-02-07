package com.colak.streams.splititerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * See <a href="https://medium.com/@mohamedanees6/deep-dive-into-java-parallel-streams-6f3ef805cead">...</a>
 */
public class SplitIteratorTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        IntStream.rangeClosed(1, 10).forEach(list::add);

        // Spliterator is able to split a list into multiple parts.
        var original = list.spliterator();
        var split1 = original.trySplit();
        var split2 = original.trySplit();

        // -----------Original--------------------------
        // 8
        // 9
        // 10
        // -----------Split1-------------------------
        // 1
        // 2
        // 3
        // 4
        // 5
        // -----------Split2--------------------------
        // 6
        // 7
        System.out.println("-----------Original--------------------------");
        original.forEachRemaining(System.out::println);
        System.out.println("-----------Split1-------------------------");
        split1.forEachRemaining(System.out::println);
        System.out.println("-----------Split2--------------------------");
        split2.forEachRemaining(System.out::println);
    }
}
