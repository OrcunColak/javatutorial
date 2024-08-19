package com.colak.gof.builder.record;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

// See https://sergiy-yevtushenko.medium.com/revisiting-fluent-builder-pattern-563b4b3fe413
// Fluent builder example
@Slf4j
public record NameAge(String firstName, String lastName, Optional<String> middleName, Optional<Integer> age) {

    static NameAgeBuilderStage1 builder() {
        return firstName -> lastName -> middleName -> age -> new NameAge(firstName, lastName, middleName, age);
    }

    public interface NameAgeBuilderStage1 {
        NameAgeBuilderStage2 firstName(String firstName);
    }

    public interface NameAgeBuilderStage2 {
        NameAgeBuilderStage3 lastName(String lastName);
    }

    public interface NameAgeBuilderStage3 {
        NameAgeBuilderStage4 middleName(Optional<String> middleName);

        default NameAgeBuilderStage4 withNoMiddleName() {
            return middleName(Optional.empty());
        }

        default NameAge withoutMiddleNameAndAge() {
            return middleName(Optional.empty()).withUnknownAge();
        }
    }

    public interface NameAgeBuilderStage4 {
        NameAge age(Optional<Integer> age);

        default NameAge withUnknownAge() {
            return age(Optional.empty());
        }
    }

    public static void main(String[] args) {
        var nameAge1 = NameAge.builder()
                .firstName("John")
                .lastName("Doe")
                .withoutMiddleNameAndAge();

        var nameAge2 = NameAge.builder()
                .firstName("John")
                .lastName("Doe")
                .middleName(Optional.of("Smith"))
                .withUnknownAge();

        var nameAge3 = NameAge.builder()
                .firstName("John")
                .lastName("Doe")
                .withNoMiddleName()
                .age(Optional.of(42));

        log.info("nameAge1 : {}", nameAge1);
        log.info("nameAge2 : {}", nameAge2);
        log.info("nameAge3 : {}", nameAge3);
    }
}
