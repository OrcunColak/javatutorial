package com.colak.codegenerator;

// See https://adityasolge.medium.com/creating-a-code-generator-processor-code-generator-3-4-f27862a2d632
class BuilderPropertyTest {

    public static void main(String[] args) {

        class Person {
            @BuilderProperty
            private String name;

            @BuilderProperty
            private int age;
        }

    }
}
