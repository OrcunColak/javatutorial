package com.colak.java9.interfaces;

/**
 * See <a href="https://rathod-ajay.medium.com/a-comprehensive-journey-from-java-8-to-java-21-with-code-examples-of-essential-api-enhancements-6817d2ab3ba8">...</a>
 */
public interface PrivateMethodInterface {
    default void publicMethod() {
        // Public method can call private method
        privateMethod();
    }

    private void privateMethod() {
        System.out.println("Private method in interface");
    }
}
