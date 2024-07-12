package com.colak.java16.datetimeformatter.localized;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map;

// See https://rathod-ajay.medium.com/a-comprehensive-journey-from-java-8-to-java-21-with-code-examples-of-essential-api-enhancements-6817d2ab3ba8
@Slf4j
@UtilityClass
public class DateTimeFormatterBuilderDayPeriodTest {

    static Map<TextStyle, Locale> map = Map.of(
            TextStyle.FULL, Locale.FRENCH,
            TextStyle.SHORT, Locale.FRENCH,
            TextStyle.NARROW, Locale.FRENCH
    );

    public static void main() {
        LocalDateTime now = LocalDateTime.now();

        // 2024-02-05 08:37 du matin
        // 2024-02-05 08:37 du matin
        // 2024-02-05 08:37 mat.
        for (var entry : map.entrySet()) {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendPattern("yyyy-MM-dd hh:mm ")
                    .appendDayPeriodText(entry.getKey())
                    .toFormatter(entry.getValue());

            String formattedDateTime = now.format(formatter);
            log.info(formattedDateTime);
        }
    }
}
