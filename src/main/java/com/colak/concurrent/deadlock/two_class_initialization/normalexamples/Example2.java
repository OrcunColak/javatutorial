package com.colak.concurrent.deadlock.two_class_initialization.normalexamples;

/**
 * See <a href="https://blog.palantir.com/using-static-analysis-to-prevent-java-class-initialization-deadlocks-c2f31ca967d6">...</a>
 */
public class Example2 {
    static Sub SUB = new Sub();
    static class Sub extends Example2 {
        private Sub() {} // cannot be instantiated externally
    }
}
