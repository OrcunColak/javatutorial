package com.colak.regex;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * See <a href="https://www.baeldung.com/java-string-split-by-newline">...</a>
 */
@Slf4j
public class SplitNewLine {

    // Java 8 provides an “\R” pattern that covers all the newline characters for different operating systems.
    public static void main(String[] args) {
        String string1 = "a\r\nb";
        String[] strings1 = string1.split("\\R");

        String string2 = "a\nb";
        String[] strings2 = string2.split("\\R");
        boolean equals = Arrays.equals(strings1, strings2);
        log.info("equals : {}", equals);
    }
}
