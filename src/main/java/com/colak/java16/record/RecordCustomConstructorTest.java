package com.colak.java16.record;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * See <a href="https://webcache.googleusercontent.com/search?q=cache:https://medium.com/@pachoyan/unveiling-java-records-tips-and-tricks-e113bd02426d">...</a>
 */
@Slf4j
class RecordCustomConstructorTest {

    record Person(String id, String department) {
        Person {
            Objects.requireNonNull(id, "Id is required");
            // Set field values before being created
            id = "%s%s".formatted("Id: ", id);

            if (department.length() > 10) {
                throw new IllegalArgumentException("Department max length is 10");
            }
        }
    }

    public static void main(String[] args) {
        Person person = new Person("1", "IT");
        log.info(person.toString());

        // java.lang.NullPointerException: Id is required
        new Person(null, "IT");
    }
}
