package com.colak.java16.datetime;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * See <a href="https://medium.com/@mobile.prabeensoti/guide-to-data-time-formatter-in-java17-65d498f5d494">...</a>
 */
@Slf4j
public class DateTimeFormatterIsoTest {
    private static final LocalDate localDate = LocalDate.of(2024, 1, 30);
    public static void main(String[] args) {
        isoFormat();
    }

    private static void isoFormat() {
        // 2024-01-30
        String format = DateTimeFormatter.ISO_LOCAL_DATE.format(localDate);
        log.info("ISO_LOCAL_DATE : {}", format);

        // 2024-01-30-05:00
        format = DateTimeFormatter.ISO_OFFSET_DATE.format(localDate
                .atStartOfDay(ZoneId.of("UTC-5")));
        log.info("ISO_LOCAL_DATE : {}", format);
    }
}
