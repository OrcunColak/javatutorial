package com.colak.java9.interfaces;

// See href="https://rathod-ajay.medium.com/a-comprehensive-journey-from-java-8-to-java-21-with-code-examples-of-essential-api-enhancements-6817d2ab3ba8

import lombok.extern.slf4j.Slf4j;

@Slf4j
class PrivateMethodInterfaceTest {

    interface MyInterface {
        default void publicMethod() {
            // Public method can call private method
            privateMethod();
        }

        private void privateMethod() {
            log.info("Private method in interface");
        }
    }

    public static void main() {
        MyInterface myInterface = new MyInterface() {
        };
        myInterface.publicMethod();
    }
}
