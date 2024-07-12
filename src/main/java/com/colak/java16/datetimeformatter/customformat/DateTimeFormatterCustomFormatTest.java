package com.colak.java16.datetimeformatter.customformat;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@UtilityClass
class DateTimeFormatterCustomFormatTest {

    public static void main() {
        parseLocalDateTime();
    }

    private static void parseLocalDateTime() {
        LocalDateTime customDateTime = LocalDateTime.now();
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDate = customDateTime.format(customFormatter);
        log.info("formatted LocalDateTime: {}", formattedDate);
    }
}
