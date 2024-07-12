package com.colak.java16.datetimeformatter.isolocaldate;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

// See https://medium.com/@mobile.prabeensoti/guide-to-data-time-formatter-in-java17-65d498f5d494
@Slf4j
@UtilityClass
class DateTimeFormatterIsoFormatTest {

    public static void main() {
        LocalDate localDate = LocalDate.of(2024, 1, 30);
        // 2024-01-30
        String format = DateTimeFormatter.ISO_LOCAL_DATE.format(localDate);
        log.info("ISO_LOCAL_DATE : {}", format);

        // 2024-01-30-05:00
        format = DateTimeFormatter.ISO_OFFSET_DATE.format(localDate.atStartOfDay(ZoneId.of("UTC-5")));
        log.info("ISO_LOCAL_DATE : {}", format);
    }
}
