package com.colak.sneakythrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * See https://medium.com/unibench/java-hack-sneaky-throws-explained-88f445ed983b
 */
public class SneakyThrowsTest {

    // We will get java.nio.file.NoSuchFileException: test.txt
    public static void main(String[] args) {
        readAllLines("test.txt");
    }

    public static void readAllLines(String filePath) {
        try {
            Files.readAllLines(Path.of(filePath))
                    .forEach(System.out::println);
        } catch (IOException exception) {
            sneakyThrows(exception);
        }
    }

    public static <E extends Throwable> void sneakyThrows(Throwable e) throws E {
        throw (E) e;
    }
}
