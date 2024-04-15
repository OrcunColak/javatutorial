package com.colak.stringformatting.messageformat;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

/**
 * See <a href="https://webcache.googleusercontent.com/search?q=cache:https://blog.devgenius.io/how-to-format-strings-in-java-1b016b047793">...</a>
 */
@Slf4j
class MessageFormatTest {

    public static void main(String[] args) {
        Object[] messageArguments = {"John", 29};

        String pattern = "My name is {0}, I am {1} years old.";
        String formattedMessage = MessageFormat.format(pattern, messageArguments);

        // "My name is John, I am 29 years old."
        log.info(formattedMessage);
    }
}
