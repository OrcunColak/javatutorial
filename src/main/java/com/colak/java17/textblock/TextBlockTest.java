package com.colak.java17.textblock;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class TextBlockTest {

    public static void main() {
        newLineTest();
        escapeSequenceTest();

    }

    private static void escapeSequenceTest() {
        String str = """
                line1 \
                line2
                """;
        // line1
        // line2
        // \n
        log.info("str : {}", str);
    }

    // Text block is not platform dependent. It only adds \n to line ending
    private static void newLineTest() {
        // line1
        // line2
        String str = """
                line1
                line2""";
        int indexOf = str.indexOf("\r\n");
        // -1
        log.info("Index : {}", indexOf);
    }
}
