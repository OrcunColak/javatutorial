package com.colak.java16.datetimeformatter.isolocaldatetime;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// See https://webcache.googleusercontent.com/search?q=cache:https://blog.stackademic.com/how-java-handles-time-format-6-different-datetimeformatter-you-should-know-about-1db0e444dea3
@Slf4j
@UtilityClass
class DateTimeFormatterIsoLocalDateTimeParseTest {

    // ISO_LOCAL_DATE_TIME is a formatter that formats or parses a date without an offset, such as 2024–08–03T10:15:30.
    public static void main() {
        String dateTimeStr = "2024-08-03T10:15:30";
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        log.info("LocalDateTime : {}", dateTime);
    }
}
