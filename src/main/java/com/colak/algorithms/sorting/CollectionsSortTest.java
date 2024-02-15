package com.colak.algorithms.sorting;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;

/**
 * See <a href="https://springjavatutorial.medium.com/sorting-arraylist-of-objects-in-java-b021d93ebbfe">...</a>
 */
@Slf4j
public class CollectionsSortTest {

    public static void main(String[] args) {
        // Changes the original list
        ArrayList<Person> testList = Person.getTestList();
        Collections.sort(testList, Person.getComparator());

        for (Person person : testList) {
            log.info(person.toString());
        }
    }
}
