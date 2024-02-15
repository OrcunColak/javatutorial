package com.colak.concurrent.completablefuture.allof;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * See <a href="https://medium.com/@rafaelmisidoro/how-return-in-one-only-list-using-completablefuture-for-java-1b2c2a452763">...</a>
 */
@Slf4j
public class AllOfTest {

    public static void main(String[] args) throws Exception {
        CompletableFuture<List<Integer>> futureList1 = CompletableFuture.supplyAsync(() -> {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            return list;
        });

        CompletableFuture<List<Integer>> futureList2 = CompletableFuture.supplyAsync(() -> {
            List<Integer> list = new ArrayList<>();
            list.add(4);
            list.add(5);
            list.add(6);
            return list;
        });

        CompletableFuture<List<Integer>> futureList3 = CompletableFuture.supplyAsync(() -> {
            List<Integer> list = new ArrayList<>();
            list.add(7);
            list.add(8);
            list.add(9);
            return list;
        });

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futureList1, futureList2, futureList3);

        CompletableFuture<List<Integer>> combinedFuture = allFutures.thenApply(v -> {
            List<Integer> result = new ArrayList<>();
            result.addAll(futureList1.join());
            result.addAll(futureList2.join());
            result.addAll(futureList3.join());
            return result;
        });

        List<Integer> result = combinedFuture.get();
        log.info("List : {}", result);
    }
}
