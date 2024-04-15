package com.colak.stringformatting.messageformat;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * See <a href="https://webcache.googleusercontent.com/search?q=cache:https://blog.devgenius.io/how-to-format-strings-in-java-1b016b047793">...</a>
 */
@Slf4j
class ResourceBundleTest {

    public static void main(String[] args) {
        Locale currentLocale = Locale.of("fr", "FR");
        ResourceBundle messages = ResourceBundle.getBundle("messages", currentLocale);

        String pattern = messages.getString("greeting");

        Object[] messageArguments = {"Jean"};
        MessageFormat formatter = new MessageFormat("");
        formatter.setLocale(currentLocale);
        formatter.applyPattern(pattern);

        // Assuming messages_fr_FR.properties contains greeting=Bonjour {0}!
        // "Bonjour Jean!"
        log.info(formatter.format(messageArguments));
    }
}
