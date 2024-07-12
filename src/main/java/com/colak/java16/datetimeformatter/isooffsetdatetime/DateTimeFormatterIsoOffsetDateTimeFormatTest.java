package com.colak.java16.datetimeformatter.isooffsetdatetime;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@UtilityClass
class DateTimeFormatterIsoOffsetDateTimeFormatTest {

    // This formatter handles date-time with an offset from Greenwich/UTC time, such as 2024–08–03T10:15:30+01:00.
    // It’s useful for representing the time with respect to the UTC/GMT offset.
    public static void main() {
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        String formattedDate = offsetDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        // 2024-04-19T10:59:46.1233494+03:00
        log.info("formattedDate : {}", formattedDate);
    }
}
