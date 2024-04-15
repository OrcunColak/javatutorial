package com.colak.stringformatting.messageformat;

import lombok.extern.slf4j.Slf4j;

import java.text.ChoiceFormat;
import java.text.MessageFormat;

/**
 * See <a href="https://webcache.googleusercontent.com/search?q=cache:https://blog.devgenius.io/how-to-format-strings-in-java-1b016b047793">...</a>
 */
@Slf4j
class ChoiceFormatTest {

    public static void main(String[] args) {
        double[] limits = {0, 1, 2};
        String[] formats = {"no files", "one file", "{0,number} files"};
        ChoiceFormat choiceFormat = new ChoiceFormat(limits, formats);

        String pattern = "I have {0} on my disk.";
        MessageFormat messageFormat = new MessageFormat(pattern);
        messageFormat.setFormatByArgumentIndex(0, choiceFormat);

        for (int fileCount = 0; fileCount < 3; fileCount++) {
            log.info(messageFormat.format(new Object[]{fileCount}));
        }
    }
}
