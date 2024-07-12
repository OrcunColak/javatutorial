package com.colak.java16.datetimeformatter.isolocaldatetime;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// See https://medium.com/@mobile.prabeensoti/guide-to-data-time-formatter-in-java17-65d498f5d494
@Slf4j
@UtilityClass
class DateTimeFormatterIsoLocalDateTimeFormatTest {

    // ISO_LOCAL_DATE_TIME is a formatter that formats or parses a date without an offset, such as 2024–08–03T10:15:30.
    public static void main() {
        LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 30, 12, 11, 10);
        // 2024-01-30T12:11:10
        String format = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDateTime);
        log.info("ISO_LOCAL_DATE_TIME : {}", format);
    }
}
