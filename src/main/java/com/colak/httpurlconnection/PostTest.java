package com.colak.httpurlconnection;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Slf4j
@UtilityClass
public class PostTest {

    private static final int TIMEOUT = 5000; // Timeout in milliseconds

    public static void main() {
        String requestBody = "{\"key\": \"value\"}";
        postData(requestBody);
    }

    private static void postData(String requestBody) {
        HttpURLConnection connection = null;
        try {
            URL url = URI.create("http://google.com").toURL();
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(TIMEOUT);
            connection.setReadTimeout(TIMEOUT);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            try (OutputStream outputStream = connection.getOutputStream();
                 OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
                outputStreamWriter.write(requestBody);
                outputStreamWriter.flush();
            }

            // Read the response if necessary
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Handle successful response
                String response = readInputStream(connection.getInputStream());

                log.info("Response: {}", response);

            } else {
                // Handle error response
                log.info("Error response code: {}", responseCode);
                String response = readInputStream(connection.getErrorStream());
                log.info("Error response: {}", response);

            }
        } catch (IOException exception) {
            log.error("Exception : ", exception);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    // Read the response
    public static String readInputStream(InputStream inputStream) throws IOException {
        // Read the response
        StringBuilder response = new StringBuilder();
        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader in = new BufferedReader(inputStreamReader)) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }
        return response.toString();
    }
}
