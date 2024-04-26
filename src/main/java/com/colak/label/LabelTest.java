package com.colak.label;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

import static java.util.Arrays.asList;

@Slf4j
class LabelTest {

    private static class TestException extends Exception {
    }

    public static void main(String[] args) {
        try {
            rethrowFromCollection(asList(new NullPointerException(), new TestException()), NullPointerException.class);
        } catch (Throwable throwable) {
            log.error("Throwable is :", throwable);
        }
    }

    @SafeVarargs
    private static void rethrowFromCollection(Collection<?> values, Class<? extends Throwable>... ignored) throws Throwable {
        outerLoop:
        for (Object value : values) {
            if (value instanceof Throwable throwable) {
                for (Class<? extends Throwable> ignoredClass : ignored) {
                    if (ignoredClass.isAssignableFrom(value.getClass())) {
                        continue outerLoop;
                    }
                }
                throw throwable;
            }
        }
    }
}
