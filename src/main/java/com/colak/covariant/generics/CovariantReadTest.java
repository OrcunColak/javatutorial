package com.colak.covariant.generics;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
// Generic Types are Invariant
class CovariantReadTest {

    public static void main() {
        List<String> aList = new ArrayList<>(List.of("Hello"));

        // read is ok
        List<? extends Object> covariantList = aList;

        print(covariantList);
    }

    private static void print(List<? extends Object> covariantList) {
        for (Object object : covariantList) {
            log.info("object = {}", object);
        }
    }
}
