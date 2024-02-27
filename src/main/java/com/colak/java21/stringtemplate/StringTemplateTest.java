package com.colak.java21.stringtemplate;

import lombok.extern.slf4j.Slf4j;

import static java.lang.StringTemplate.STR;


@Slf4j
public class StringTemplateTest {

    public static void main(String[] args) {
        StringTemplateTest stringTemplateTest = new StringTemplateTest();
        stringTemplateTest.singleLine();
        stringTemplateTest.multiline();
    }

    private void singleLine() {
        String firstName = "John";
        String lastName = "Doe";
        int age = 30;
        String city = "New York";

        String userInfo = STR."FirstName: \{firstName} LastName: \{lastName} Age: \{age} City: \{city}";
        log.info("single line {}", userInfo);
    }

    private void multiline() {
        String name = "Joan Smith";
        String phone = "555-123-4567";
        String address = "1 Maple Drive, Anytown";
        String json = STR. """
                {
                    "name":    "\{ name }",
                    "phone":   "\{ phone }",
                    "address": "\{ address }"
                }
                """ ;
        log.info("multi line JSON {}", json);
    }
}