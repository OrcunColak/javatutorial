package com.colak.algorithms.sorting;

import java.util.ArrayList;
import java.util.Comparator;

public record Person(int id, String firstName, String lastName) {

    public static ArrayList<Person> getTestList() {
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person(1, "Peter", "Parker"));
        list.add(new Person(2, "Robert", "John"));
        list.add(new Person(3, "Bill", "Smith"));
        return list;
    }

    public static Comparator<Person> getComparator() {
        return Comparator
                .comparing(Person::firstName)
                .thenComparing(Person::lastName)
                .reversed();
    }
}
