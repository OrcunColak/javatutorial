package com.colak.oom;

import java.util.ArrayList;
import java.util.List;

// -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=foo.hprof
// Use visual vm to load the file
public class OOMTest {

    public static void main(String[] args) {
        try {
            generateOutOfMemory();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
    }

    private static void generateOutOfMemory() {
        List<Object> list = new ArrayList<>();
        try {
            while (true) {
                list.add(new byte[1024 * 1024]); // Allocating 1MB of memory in each iteration
            }
        } catch (OutOfMemoryError e) {
            System.out.println("OutOfMemoryError occurred!");
            throw e;
        }
    }
}
