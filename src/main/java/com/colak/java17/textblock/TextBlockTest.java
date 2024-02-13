package com.colak.java17.textblock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TextBlockTest {

    public static void main(String[] args) {
        newLineTest();
        escapeSequenceTest();

    }

    private static void escapeSequenceTest() {
        String str = """
                line1 \
                line2
                """;
        // -1
        log.info("str : {}", str);
    }

    // Text block is not platform dependent. It only adds \n to line ending
    private static void newLineTest() {
        // line1 line2
        String str = """
                line1
                line2
                """;
        int indexOf = str.indexOf("\r\n");
        log.info("Index : {}", indexOf);
    }
}
