package com.colak.datastructures.list;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Slf4j
class ListReplaceAllTest {

    public static void main() {
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        words.replaceAll(String::toUpperCase); // Modify all elements
        words.sort(Comparator.reverseOrder()); // Sort the list

        // List : [CHERRY, BANANA, APPLE]
        log.info("List : {}", words);

    }
}
