package com.colak.algorithms.sorting;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * See <a href="https://springjavatutorial.medium.com/sorting-arraylist-of-objects-in-java-b021d93ebbfe">...</a>
 */
@Slf4j
public class ListSortTest {

    public static void main(String[] args) {
        // Changes the original list
        ArrayList<Person> testList = Person.getTestList();
        testList.sort(Person.getComparator());

        for (Person person : testList) {
            log.info(person.toString());
        }
    }
}
