package com.colak.java16.datetime.isodatetime;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
class DateTimeFormatterIsoDateTimeFormatTest {

    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDate = dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        // formattedDate : 2024-04-19T11:06:03.9956547
        log.info("formattedDate : {}", formattedDate);
    }
}
