package com.colak.java16.record;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * See <a href="https://webcache.googleusercontent.com/search?q=cache:https://medium.com/@pachoyan/unveiling-java-records-tips-and-tricks-e113bd02426d">...</a>
 */
@Slf4j
class RecordLombokBuilderTest {

    @Builder
    public record PersonRecord(
            String id,
            String lastName) {
    }

    public static void main(String[] args) {
        PersonRecord person1 = PersonRecord.builder()
                .id("1")
                .lastName("Smith")
                .build();
        log.info(person1.toString());
    }
}
