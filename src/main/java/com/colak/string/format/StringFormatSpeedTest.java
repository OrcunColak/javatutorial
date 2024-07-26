package com.colak.string.format;

import lombok.experimental.UtilityClass;

import java.text.NumberFormat;
import java.util.Locale;

@UtilityClass
public class StringFormatSpeedTest {

    public static void main() {
        long startTime, endTime;
        String name = "World";

        // Concatenation Benchmark
        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            String result = "Hello, " + name + "!";
        }
        endTime = System.nanoTime();
        long concatenationTime = endTime - startTime;

        // Formatting Benchmark
        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            String result = String.format("Hello, %s!", name);
        }
        endTime = System.nanoTime();
        long formattingTime = endTime - startTime;

        // Format numbers with commas for readability
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

        // Right-align the output with formatted numbers
        System.out.println(String.format("%-15s: %15s ns", "Concatenation", numberFormat.format(concatenationTime)));
        System.out.println(String.format("%-15s: %15s ns", "Formatting", numberFormat.format(formattingTime)));
    }
}