package com.colak.algorithms.sorting;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * See <a href="https://springjavatutorial.medium.com/sorting-arraylist-of-objects-in-java-b021d93ebbfe">...</a>
 */
@Slf4j
public class StreamSortTest {

    public static void main(String[] args) {
        ArrayList<Person> sortedList = Person.getTestList().stream()
                .sorted(Person.getComparator())
                .collect(Collectors.toCollection(ArrayList::new));

        for (Person person : sortedList) {
            log.info(person.toString());
        }
    }
}
