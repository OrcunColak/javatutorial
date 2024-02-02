package com.colak.java21.recordpattern;

import lombok.extern.slf4j.Slf4j;

/**
 * See <a href="https://bayramblog.medium.com/java-21s-record-patterns-a-modern-twist-on-data-handling-520dc2a1a380">...</a>
 */
@Slf4j
public class RecordPatternTest {

    // A simple Record for representing a point in 2D space
    record Point(int x, int y) {
    }

    record Size(int width, int height) {
    }

    record WindowFrame(Point origin, Size size) {
    }

    public static void main(String[] args) {
        Object obj = new Point(1, 2);
        WindowFrame windowFrame = new WindowFrame(new Point(1, 2), new Size(3, 4));

        recordPattern(obj);
        nestedRecordPattern(windowFrame);

    }

    private static void recordPattern(Object obj) {
        if (obj instanceof Point(int x, int y)) {
            // Access x and y directly, without casting
            log.info("x : {}, y : {}", x, y);
        }
    }

    private static void nestedRecordPattern(Object obj) {
        if (obj instanceof WindowFrame(Point origin, Size(int width, int height))) {
            // Access width and height directly, code is more concise
            log.info("width : {}, height : {}", width, height);
        }
    }
}
