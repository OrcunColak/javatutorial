package com.colak.annotation.retention;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * See https://medium.com/@husnapoyraz88/java-retention-anatasyonu-255f5e651575
 */
@Slf4j
class RetentionTest {

    @MyMultiValueAnnotation
    public void displayHi() {
        log.info("Hi");
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Method method = RetentionTest.class.getMethod("displayHi");

        MyMultiValueAnnotation myMultiValueAnnotation = method.getAnnotation(MyMultiValueAnnotation.class);

        log.info("value is: " + myMultiValueAnnotation.getValue());
        log.info("message is: " + myMultiValueAnnotation.getMessage());
        log.info("DoubleValue is: " + myMultiValueAnnotation.getDoubleValue());
    }
}
