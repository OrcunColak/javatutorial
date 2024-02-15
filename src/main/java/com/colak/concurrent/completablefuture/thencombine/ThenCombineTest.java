package com.colak.concurrent.completablefuture.thencombine;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * See <a href="https://medium.com/@sj.anyway/asynchronous-programming-in-java-with-completablefuture-2e6d7a6d43e0">...</a>
 */
@Slf4j
public class ThenCombineTest {

    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello, ");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "CompletableFuture!");

        CompletableFuture<String> combined = future1.thenCombine(future2, (result1, result2) -> {
            // main thread
            log.info("thenCombine");
            return result1 + result2;
        });

        // main thread
        combined.thenAccept(log::info);
    }
}
