package com.colak.generics;

import java.util.ArrayList;
import java.util.List;

class ContravariantAssignmentTest {

    public static void main(String[] args) {
        List<String> aList = new ArrayList<>(List.of("Hello"));

        // Covariant types are read-only,
        List<? extends Object> covariantList = aList;

        // Contravariant types are write-only.
        List<? super String> contravariantList = aList;
        contravariantList.add("World");

        // compilation error
        // contravariantList.add(1);

        print(covariantList);
    }

    private static void print(List<? extends Object> covariantList) {
        for (Object object : covariantList) {
            System.out.println("object = " + object);
        }
    }


}
