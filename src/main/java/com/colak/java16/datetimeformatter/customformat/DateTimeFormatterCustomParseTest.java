package com.colak.java16.datetimeformatter.customformat;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@UtilityClass
class DateTimeFormatterCustomParseTest {

    public static void main() {
        parseLocalDateTime();
        parseLocalDate();
    }

    private static void parseLocalDateTime() {
        String customDateTimeStr = "03/04/2024 10:15:30";
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(customDateTimeStr, customFormatter);

        // LocalDateTime: 2024-04-03T10:15:30
        log.info("LocalDateTime: {}", localDateTime);
    }

    private static void parseLocalDate() {
        String date = "2024-07-12";
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, customFormatter);
        log.info("LocalDate: {}", localDate);
    }
}
