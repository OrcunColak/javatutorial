package com.colak.java16.datetimeformatter.isozoneddatetime;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@UtilityClass
class DateTimeFormatterIsoZonedDateTimeParseTest {

    // When you need to include the timezone in your formatted output, ISO_ZONED_DATE_TIME is the formatter to use.
    //
    // It'll produce strings like 2024-04-03T10:15:30+01:00[Europe/Paris].
    public static void main() {
        String zonedDateTimeStr = "2024-04-03T10:15:30+01:00[Europe/Paris]";
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(zonedDateTimeStr, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        // ZonedDateTime : 2024-04-03T11:15:30+02:00[Europe/Paris]
        log.info("ZonedDateTime : {}", zonedDateTime);
    }
}
