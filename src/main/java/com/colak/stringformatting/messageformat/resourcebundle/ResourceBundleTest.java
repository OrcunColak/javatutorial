package com.colak.stringformatting.messageformat.resourcebundle;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

@Slf4j
class ResourceBundleTest {

    public static void main() {
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
