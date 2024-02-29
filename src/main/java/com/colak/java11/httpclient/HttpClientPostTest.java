package com.colak.java11.httpclient;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

@Slf4j
class HttpClientPostTest {

    public static void main(String[] args) throws IOException, InterruptedException {

        HttpClientPostTest httpClientPostTest = new HttpClientPostTest();
        httpClientPostTest.postExample();
        httpClientPostTest.postAsyncExample();
    }

    // See https://medium.com/@satyendra.jaiswal/webhooks-and-asynchronous-apis-real-time-communication-patterns-b6dee06b855d
    private void postExample() throws IOException, InterruptedException {
        String url = "https://example.com/webhook/subscribe";
        String payload = "{\"event\": \"user_subscription\", \"user\": \"john_doe\"}";

        try (HttpClient client = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            log.info("Webhook response code: " + response.statusCode());
        }
    }

    private void postAsyncExample() {
        String url = "https://example.com/webhook/subscribe";

        try (HttpClient client = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();

            CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

            // Process the response
            responseFuture.thenAccept(response -> {
                if (response.statusCode() == 200) {
                    log.info("Subscription successful");

                } else {
                    log.error("Failed. Status code: " + response.statusCode());
                }
            }).join(); // Blocking, used for demonstration purposes
        }
    }
}
