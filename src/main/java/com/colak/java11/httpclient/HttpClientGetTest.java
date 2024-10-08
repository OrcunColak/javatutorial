package com.colak.java11.httpclient;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

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
class HttpClientGetTest {

    public static void main() throws InterruptedException {
        HttpClientGetTest httpClientGetTest = new HttpClientGetTest();
        httpClientGetTest.getExample();
        httpClientGetTest.getAsyncExample();
        httpClientGetTest.getWithConnectionTimeoutExample();
        httpClientGetTest.getWithRequestTimeoutExample();
    }


    private void getExample() throws InterruptedException {
        try (HttpClient client = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.google.com/"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Response Code: {}", response.statusCode());
            log.info("Response Body: {}", response.body());
        } catch (IOException exception) {
            log.error("Exception :" , exception);
        }
    }

    private void getAsyncExample() {
        try (HttpClient client = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.google.com/"))
                    .GET()
                    .build();
            CompletableFuture<HttpResponse<String>> futureResponse = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            futureResponse.thenAccept(response -> {
                log.info("Async Response Code: {}", response.statusCode());
                log.info("Async Response Body: {}", response.body());
            });
            // Wait for the asynchronous request to complete
            futureResponse.join();
        }
    }

    private void getWithConnectionTimeoutExample() throws InterruptedException {
        try (HttpClient client = HttpClient.newBuilder()
                .connectTimeout(java.time.Duration.ofSeconds(10))
                .build()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.nonexistingdomain.com/"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.info(response.body());
        } catch (IOException exception) {
            log.error("Exception :" , exception);
        }
    }

    private void getWithRequestTimeoutExample() throws InterruptedException {
        try (HttpClient client = HttpClient.newBuilder()
                .build()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.google.com/"))
                    .header("Accept", "application/json")
                    .timeout(Duration.ofSeconds(10))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Response Code: {}", response.statusCode());
            log.info("Response Body: {}", response.body());
        } catch (IOException exception) {
            log.error("Exception :" , exception);
        }
    }
}
