package com.colak.lombok.value;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;

// The @Value annotation in Lombok helps create immutable Java objects.
// Using @Value, all fields become private final with getters methods and are included in the constructor.
// It also overrides toString(), hashCode(), and equals() methods.
// However, if you’re using Java17, consider using the record type for a better alternative.
@Value
@Slf4j
class ValueExample {
    String name;
    Integer age;

    public static void main() {
        ValueExample valueExample = new ValueExample("John", 30);
        log.info("Name: {}", valueExample.getName()); // Name: John
        log.info("Age: {}", valueExample.getAge()); // Age: 30

    }
}
