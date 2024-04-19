package com.colak.java16.datetime.customformat;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
class DateTimeFormatterCustomFormatTest {

    public static void main(String[] args) {
        LocalDateTime customDateTime = LocalDateTime.now();
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDate = customDateTime.format(customFormatter);
        log.info("formattedDate: {}", formattedDate);
    }
}
