package com.colak.java22;

import java.util.List;

/**
 * See <a href="https://medium.com/@benweidig/looking-at-java-22-statements-before-super-298bc9ee4d89">...</a>
 */
public class BeforeSuper_ClassTest {

    public static void main(String[] args) {
        // => SuperClass: one
        new SubClass(List.of("One", "Two", "Three"));

        // => SuperClass: <n/a>
        new SubClass(null);
    }

    private static class SuperClass {

        public SuperClass(String element) {
            System.out.println("SuperClass: " + element);
        }
    }

    private static class SubClass extends SuperClass {

        public SubClass(List<String> data) {
            String element;
            if (data != null && !data.isEmpty()) {
                element = data.get(0).toLowerCase();
            } else {
                element = "<n/a>";
            }
            super(element);
        }
    }
}

