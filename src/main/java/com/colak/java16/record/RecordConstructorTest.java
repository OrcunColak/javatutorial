package com.colak.java16.record;

import lombok.extern.slf4j.Slf4j;

/**
 * See <a href="https://webcache.googleusercontent.com/search?q=cache:https://medium.com/@pachoyan/unveiling-java-records-tips-and-tricks-e113bd02426d">...</a>
 */
@Slf4j
class RecordConstructorTest {

    public record PersonRecord(String id, String department) {
        public PersonRecord(String id) {
            this(id, "finance");
        }

        public PersonRecord() {
            this("1", "hr");
        }
    }

    public static void main(String[] args) {
        PersonRecord person1 = new PersonRecord();
        PersonRecord person2 = new PersonRecord("1");
        PersonRecord person3 = new PersonRecord("2", "IT");

        log.info(person1.toString());
        log.info(person2.toString());
        log.info(person3.toString());

    }
}
