package com.colak.java11.httpclient;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
class HttpClientHeadTest {

    public static void main(String[] args) throws Exception {
        HttpClientHeadTest httpClientHeadTest = new HttpClientHeadTest();
        httpClientHeadTest.headExample();
    }

    private void headExample() throws IOException, InterruptedException {
        try (HttpClient client = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.google.com/"))
                    .HEAD()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Response Code: {}", response.statusCode());
            log.info("Response Body: {}", response.body());
        }
    }
}
