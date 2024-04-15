package com.colak.stringformatting.stringformat;

import lombok.extern.slf4j.Slf4j;

/**
 * See <a href="https://webcache.googleusercontent.com/search?q=cache:https://blog.devgenius.io/how-to-format-strings-in-java-1b016b047793">...</a>
 */
@Slf4j
class StringFormatWidthTest {

    public static void main(String[] args) {
        log.info("-----------------RIGHT JUSTIFY-----------------");
        rightJustify();
        log.info("-----------------LEFT JUSTIFY-----------------");
        leftJustify();
    }

    private static void rightJustify() {
        // "|75|"
        log.info(String.format("|%1d|", 75));

        // "|75|"
        log.info(String.format("|%2d|", 75));

        // "| 75|"
        log.info(String.format("|%3d|", 75));

        // "|  75|"
        log.info(String.format("|%4d|", 75));
    }

    // - flag will left-justify the output wit
    private static void leftJustify() {
        // "|75|"
        log.info(String.format("|%-1d|", 75));

        // "|75|"
        log.info(String.format("|%-2d|", 75));

        // "|75 |"
        log.info(String.format("|%-3d|", 75));

        // "|75  |"
        log.info(String.format("|%-4d|", 75));
    }
}
