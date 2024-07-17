package com.colak.httpurlconnection;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

@Slf4j
@UtilityClass
public class GetTest {

    public static void main() {
        getData();
    }

    private static void getData() {
        HttpURLConnection connection = null;
        try {
            URL url = URI.create("http://google.com").toURL();

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(2_000);

            // This call will connect to given URL
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                String response = PostTest.readInputStream(connection.getInputStream());
                log.info(response);
            } else {
                log.info("GET request failed");
            }
        } catch (IOException exception) {
            log.error("Exception : ", exception);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
