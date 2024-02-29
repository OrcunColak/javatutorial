package com.colak.java8.atomicinteger;

import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * See <a href="https://medium.com/@abhirup.acharya009/managing-concurrent-access-optimistic-locking-vs-pessimistic-locking-0f6a64294db7">...</a>
 */
@Slf4j
class CompareAndSetTest {
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        int numThreads = 100_000;
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                int localCounter;

                do {
                    localCounter = counter.get(); // Get the current counter value

                    // Compare-and-swap operation
                } while (!counter.compareAndSet(localCounter, localCounter + 1));
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        Locale locale = Locale.of("tr", "TR");
        NumberFormat formatter = NumberFormat.getInstance(locale);
        formatter.setGroupingUsed(true);

        log.info("Counter value after optimistic locking: {}", formatter.format(counter));
    }
}
