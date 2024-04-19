package com.colak.java16.datetime.isoinstant;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Slf4j
class DateTimeFormatterIsoInstantFormatTest {

    public static void main(String[] args) {
        Instant instant = Instant.now();
        String formattedDate = DateTimeFormatter.ISO_INSTANT.format(instant);

        // formattedDate: 2024-04-19T08:09:21.496587500Z
        log.info("formattedDate: {}", formattedDate);
    }
}
