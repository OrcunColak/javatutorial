package com.colak.java16.datetimeformatter.isodatetime;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@UtilityClass
class DateTimeFormatterIsoDateTimeFormatTest {

    public static void main() {
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDate = dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
        // formattedDate : 2024-04-19T11:06:03.9956547
        log.info("formattedDate : {}", formattedDate);
    }
}
