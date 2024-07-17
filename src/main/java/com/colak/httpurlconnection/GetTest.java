package com.colak.httpurlconnection;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Slf4j
@UtilityClass
public class GetTest {

    public static void main() throws IOException {
        URL url = URI.create("http://google.com").toURL();

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(2_000);
        // connection.setReadTimeout(2_000);

        // This call will connect to given URL
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            StringBuilder response = new StringBuilder();

            try (InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
                 BufferedReader in = new BufferedReader(inputStreamReader)) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            log.info(response.toString());
        } else {
            log.info("GET request failed");
        }
        connection.disconnect();
    }
}
