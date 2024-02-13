package com.colak.java21.enhancedswitch;

import lombok.extern.slf4j.Slf4j;


/**
 * See <a href="https://bayramblog.medium.com/embracing-modern-java-the-evolution-of-switch-in-java-21-199cf999841a">...</a>
 */
@Slf4j
public class EnhancedSwitchTest {

    record Person(String firstName, String lastName, int age) {
    }

    public static void main(String[] args) {
        nullHandling(null);
        nullHandling(2);

        Person child = new Person("Jane", "Doe", 17);
        recordPattern(child);

        Person parent = new Person("Jane", "Doe", 21);
        recordPattern(parent);
    }


    private static void nullHandling(Integer integer) {
        String result = switch (integer) {
            case 1 -> "1";
            case null -> "null";
            default -> "default";
        };
        log.info("nullHandling result : {}", result);
    }

    private static void recordPattern(Person person) {
        String fullName = switch (person) {
            // Guard condition in case label example
            case Person p when p.age < 18 -> "Can not disclose name";
            // Record Pattern example
            case Person(var firstName, var lastName, int _) -> lastName + ", " + firstName;
        };
        log.info("recordPattern fullName : {}", fullName);
    }
}
