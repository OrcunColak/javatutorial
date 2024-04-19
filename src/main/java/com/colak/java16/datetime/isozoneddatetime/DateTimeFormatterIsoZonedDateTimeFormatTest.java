package com.colak.java16.datetime.isozoneddatetime;

import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
class DateTimeFormatterIsoZonedDateTimeFormatTest {

    // When you need to include the timezone in your formatted output, ISO_ZONED_DATE_TIME is the formatter to use.
    //
    // It'll produce strings like 2024-04-03T10:15:30+01:00[Europe/Paris].
    public static void main(String[] args) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        String formattedDate = zonedDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
        // 2024-04-19T11:02:13.5240283+03:00[Europe/Istanbul]
        log.info("formattedDate : {}", formattedDate);
    }
}
