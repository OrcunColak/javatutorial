package com.colak.convariant.generics;

import java.util.Arrays;
import java.util.List;

// Generic Types are Invariant
class ContravariantAssignmentTest {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(100, 200);

        // Compile error
        // List<Number> nums = list;

    }
}
