package com.colak.covariant.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CovariantCopyTest {

    public static void main(String[] args) {
        List<Integer> myInts = Arrays.asList(1,2,3,4);
        List<Double> myDoubles = Arrays.asList(3.14, 6.28);

        List<Object> myObjs = new ArrayList<>();
        copy(myInts, myObjs);
        copy(myDoubles, myObjs);
    }

    // Covariance: accept subtypes : Covariant types are read-only,
    // Contravariance: accept supertypes : Contravariant types are write-only.
    private static void copy(List<? extends Number> covariantSource, List<? super Number> contraVariantDestiny) {
        for(Number number : covariantSource) {
            contraVariantDestiny.add(number);
        }
    }
}
