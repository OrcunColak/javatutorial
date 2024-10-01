package com.colak.gof.decorator.lambda;

import lombok.extern.slf4j.Slf4j;

// See https://medium.com/@bubu.tripathy/decorating-using-lambda-expressions-063ae82a7aa2
@Slf4j
public class DecoratorLambdaTest {

    interface EmailSender {
        void send(String recipient, String message);
    }

    public static void main() {
        EmailSender emailSender = getEmailSender();
        emailSender.send("customer@example.com", "Your order has been shipped!");
    }

    private static EmailSender getEmailSender() {
        EmailSender basicSender = (recipient, message) -> log.info("Sending email to {} : {}", recipient, message);

        // Adding logging feature
        EmailSender loggedSender = (recipient, message) -> {
            log.info("Logging: Sending email to {}", recipient);
            basicSender.send(recipient, message);
        };

        // Adding encryption feature
        EmailSender encryptedSender = (recipient, message) -> {
            log.info("Encrypting message for {}", recipient);
            loggedSender.send(recipient, message);
        };

        // Adding signature feature
        return (recipient, message) -> {
            String signedMessage = String.format("""
                    %s

                    Best regards,
                    E-Commerce Team""", message);
            log.info("Adding signature to message for {}", recipient);
            encryptedSender.send(recipient, signedMessage);
        };
    }
}
