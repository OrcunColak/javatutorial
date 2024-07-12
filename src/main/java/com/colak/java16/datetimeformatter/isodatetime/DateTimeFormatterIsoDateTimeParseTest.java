package com.colak.java16.datetimeformatter.isodatetime;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@UtilityClass
class DateTimeFormatterIsoDateTimeParseTest {

    public static void main() {
        String dateTimeStr = "2024-04-03T10:15:30+01:00";
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ISO_DATE_TIME);

        // LocalDateTime: 2024-04-03T10:15:30
        log.info("LocalDateTime: {}", dateTime);
    }
}
