package com.colak.httpclient;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * The HttpClient class in Java provides a modern, flexible, and non-blocking way to send HTTP requests and receive
 * HTTP responses.It was introduced in Java 9 as part of the java.net.http package.
 * <p>
 * Here's a basic overview of using HttpClient:
 * <p>
 * To create an instance of HttpClient, you can use the newBuilder() method and configure it with options like
 * timeouts, custom SSLContext, or Proxy.
 */
@Slf4j
public class HttpClientTest {

    public static void main(String[] args) throws Exception {
        HttpClientTest httpClientTest = new HttpClientTest();
        httpClientTest.getExample();
    }


    private void getExample() throws IOException, InterruptedException {

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.google.com/"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.info(response.body());
        }
    }


}
