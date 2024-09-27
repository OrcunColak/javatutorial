package com.colak.email;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.mail.Authenticator;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

// See https://readmedium.com/send-an-email-with-java-17ad67b24cfb
public class GmailTest {

    private static void gmail() {
        final String username = "your_email@gmail.com";
        final String password = "password";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // TLS

        // Create session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));

            String[] recipientEmails = {"recipient1@example.com", "recipient2@example.com", "recipient3@example.com"};
            // Add multiple recipients
            for (String recipientEmail : recipientEmails) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            }
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("cc@gmail.com")); // Carbon Copy
            message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse("bcc@gmail.com")); // Blind Carbon Copy

            // Set UTF-8 encoded subject
            String subject = "Subject with UTF-8 Characters: こんにちは";
            message.setSubject(subject, StandardCharsets.UTF_8.name());

            Multipart multipart = new MimeMultipart();

            setSubject(multipart);

            attachFiles(multipart);

            // Set the multipart message content
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setSubject(Multipart multipart) throws MessagingException {
        // Create the message body part
        String htmlBody = "<html><body><p>Hello, this is an HTML email with UTF-8 characters: こんにちは</p></body></html>";
        // Create the HTML body part
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(htmlBody, "text/html; charset=utf-8");

        multipart.addBodyPart(messageBodyPart);
    }

    private static void attachFiles(Multipart multipart) throws MessagingException, IOException {
        List<String> filePaths = new ArrayList<>(Arrays.asList("monthly_report.pdf", "weekly_report.pdf", "yearly_report.pdf"));


        // Attach files
        for (String filePath : filePaths) {
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(filePath);

            File file = new File(filePath); // Path to the file
            String base64EncodedFile = encodeFileToBase64(file);
            DataSource source = new ByteArrayDataSource(base64EncodedFile, "application/octet-stream");
            attachmentPart.setDataHandler(new DataHandler(source));

            multipart.addBodyPart(attachmentPart);
        }
    }

    private static String encodeFileToBase64(File file) {
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
