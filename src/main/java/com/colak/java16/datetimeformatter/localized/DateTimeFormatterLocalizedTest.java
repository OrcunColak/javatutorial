package com.colak.java16.datetimeformatter.localized;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

// See <a href="https://medium.com/@mobile.prabeensoti/guide-to-data-time-formatter-in-java17-65d498f5d494
@Slf4j
@UtilityClass
public class DateTimeFormatterLocalizedTest {
    private static final LocalDate localDate = LocalDate.of(2024, 1, 30);

    public static void main() {
        localizedFormat();
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
