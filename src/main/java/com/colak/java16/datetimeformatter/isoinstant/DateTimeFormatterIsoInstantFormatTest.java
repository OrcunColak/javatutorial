package com.colak.java16.datetimeformatter.isoinstant;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Slf4j
@UtilityClass
class DateTimeFormatterIsoInstantFormatTest {

    public static void main() {
        Instant instant = Instant.now();
        String formattedDate = DateTimeFormatter.ISO_INSTANT.format(instant);

        // formattedDate: 2024-04-19T08:09:21.496587500Z
        log.info("formattedDate: {}", formattedDate);
    }
}
