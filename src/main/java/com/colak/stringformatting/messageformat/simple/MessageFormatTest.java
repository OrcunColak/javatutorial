package com.colak.stringformatting.messageformat.simple;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

@Slf4j
class MessageFormatTest {

    public static void main() {
        Object[] messageArguments = {"John", 29};

        String pattern = "My name is {0}, I am {1} years old.";
        String formattedMessage = MessageFormat.format(pattern, messageArguments);

        // "My name is John, I am 29 years old."
        log.info(formattedMessage);
    }
}
