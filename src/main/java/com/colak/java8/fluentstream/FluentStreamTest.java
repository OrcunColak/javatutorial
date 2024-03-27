package com.colak.java8.fluentstream;

import java.util.Arrays;
import java.util.List;

class FluentStreamTest {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        FluentStream.from(numbers)
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .forEach(System.out::println);
    }
}
