package com.colak.collections;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@UtilityClass
class AddAllTest {

    private static final Set<String> classes = new HashSet<>();
    private static final String[] prefixes = new String[]{"class3", "class4"};

    public static void main() {
        addClasses("class1", "class2");
        log.info("Set : {}", classes);
    }

    // Use Collections.addAll to add an array to collection
    private static void addClasses(String... names) {
        Collections.addAll(classes, names);
        Collections.addAll(classes, prefixes);
    }
}
