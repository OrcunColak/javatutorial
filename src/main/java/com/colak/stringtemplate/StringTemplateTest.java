package com.colak.stringtemplate;

import lombok.extern.slf4j.Slf4j;

import static java.lang.StringTemplate.STR;


@Slf4j
public class StringTemplateTest {

    public static void main(String[] args) {
        StringTemplateTest stringTemplateTest = new StringTemplateTest();
        stringTemplateTest.multiline();
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
        log.info("multi line {}", json);
    }
}