package com.colak.datetime;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * See <a href="https://medium.com/@mobile.prabeensoti/guide-to-data-time-formatter-in-java17-65d498f5d494">...</a>
 */
@Slf4j
public class DateTimeFormatterTest {
    private static final LocalDate localDate = LocalDate.of(2024, 1, 30);
    public static void main(String[] args) {

        isoFormat();

        localizedFormat();
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

    private static void localizedFormat() {
        // 30 Ocak 2024
        // 30 Ocak 2024
        // 30 Oca 2024
        // 30.01.2024
        log.info(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(localDate));
        log.info(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(localDate));
        log.info(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(localDate));
        log.info(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(localDate));
    }
}
