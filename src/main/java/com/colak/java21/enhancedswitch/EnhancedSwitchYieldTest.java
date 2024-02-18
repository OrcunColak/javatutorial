package com.colak.java21.enhancedswitch;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class EnhancedSwitchYieldTest {

    enum DayEnum {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public static void main(String[] args) {
        weekDay(DayEnum.TUESDAY);
        weekDay(DayEnum.SATURDAY);
    }

    private static void weekDay(DayEnum dayEnum) {
        int result = switch (dayEnum) {
            case DayEnum.MONDAY, DayEnum.TUESDAY, DayEnum.WEDNESDAY, DayEnum.THURSDAY, DayEnum.FRIDAY -> {
                log.info("{} is a weekday!", dayEnum.name());
                yield 5; // Yielding a value from the switch expression
            }
            case DayEnum.SATURDAY, DayEnum.SUNDAY -> {
                log.info("{} is the weekend!", dayEnum.name());
                yield 2; // Yielding a different value
            }
        };
        log.info("Result : {}", result);
    }

}
