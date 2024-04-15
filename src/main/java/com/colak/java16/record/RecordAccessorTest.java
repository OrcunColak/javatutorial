package com.colak.java16.record;

import lombok.extern.slf4j.Slf4j;

/**
 * See <a href="https://webcache.googleusercontent.com/search?q=cache:https://medium.com/@pachoyan/unveiling-java-records-tips-and-tricks-e113bd02426d">...</a>
 */
@Slf4j
class RecordAccessorTest {

    public record PersonRecord(String id, String department) {

        // it is possible to override the accessor department()
        @Override
        public String department() {
            return "Department: " + department;
        }
    }

    public static void main(String[] args) {
        PersonRecord person = new PersonRecord("2", "IT");
        log.info(person.department());
    }
}
