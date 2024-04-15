package com.colak.java16.record;

import lombok.extern.slf4j.Slf4j;

/**
 * See <a href="https://webcache.googleusercontent.com/search?q=cache:https://medium.com/@pachoyan/unveiling-java-records-tips-and-tricks-e113bd02426d">...</a>
 */
@Slf4j
class RecordInheritanceTest {

    public interface Worker {
        void work();
    }

    public record PersonRecord(String id, String department) implements Worker {
        public PersonRecord {
            id = "%s%s".formatted("Id: ", id);
        }

        @Override
        public void work() {
            log.info("I am working!");
        }
    }

    public static void main(String[] args) {
        PersonRecord person = new PersonRecord("2", "IT");
        person.work();
    }
}
