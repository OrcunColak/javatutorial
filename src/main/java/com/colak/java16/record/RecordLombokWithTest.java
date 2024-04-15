package com.colak.java16.record;

import lombok.With;
import lombok.extern.slf4j.Slf4j;

/**
 * See <a href="https://webcache.googleusercontent.com/search?q=cache:https://medium.com/@pachoyan/unveiling-java-records-tips-and-tricks-e113bd02426d">...</a>
 */
@Slf4j
class RecordLombokWithTest {

    @With
    public record PersonRecord(
            String id,
            String lastName) {
    }

    public static void main(String[] args) {
        PersonRecord person1 = new PersonRecord("1", "Smith");
        log.info(person1.toString());

        // will change 'id' only
        PersonRecord person2 = person1.withId("2");
        log.info(person2.toString());
    }
}
