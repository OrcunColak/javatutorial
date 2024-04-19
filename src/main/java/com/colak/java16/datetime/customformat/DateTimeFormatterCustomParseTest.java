package com.colak.java16.datetime.customformat;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
class DateTimeFormatterCustomParseTest {

    public static void main(String[] args) {
        String customDateTimeStr = "03/04/2024 10:15:30";
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime customDateTime = LocalDateTime.parse(customDateTimeStr, customFormatter);

        // LocalDateTime: 2024-04-03T10:15:30
        log.info("LocalDateTime: {}", customDateTime);
    }
}
