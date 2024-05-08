package com.colak.convariant.generics;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
class CovariantWriteTest {

    public static void main() {
        List<String> aList = new ArrayList<>(List.of("Hello"));

        // write is ok
        List<? super String> covariantList = aList;
        covariantList.add("World");

        // Hello World
        log.info("List : {}", covariantList);

    }
}
