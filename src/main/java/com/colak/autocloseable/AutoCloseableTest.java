package com.colak.autocloseable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class AutoCloseableTest {


    static class MyClass implements AutoCloseable {
        void test() {
            log.info("test");
        }

        @Override
        public void close() {
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        try (MyClass myClass = new MyClass()) {
            myClass.test();
        } catch (Exception exception) {
            log.info("Exception caught", exception);
        }
    }
}
