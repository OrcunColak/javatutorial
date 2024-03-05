package com.colak.algorithms.ratelimiter;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * See <a href="https://medium.com/@devenchan/implementing-rate-limiting-in-java-from-scratch-fixed-window-and-sliding-window-implementation-a6e8d6407d17">...</a>
 */
public class SlidingWindowRateLimiter implements RateLimiter {
    final int threshold;
    final long windowUnit = 1000L;
    final Queue<Long> log = new ConcurrentLinkedQueue<>();

    public SlidingWindowRateLimiter(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public boolean tryAcquire() {
        long currentTime = System.currentTimeMillis();

        // Evict expired timestamps from the head of the queue.
        while (!log.isEmpty() && (currentTime - log.peek() > windowUnit)) {
            log.poll();
        }

        // If the queue size is below the threshold, the request is allowed.
        if (log.size() < threshold) {
            log.offer(currentTime);  // Record the timestamp of the allowed request.
            return true;
        }

        // If the queue is full, the request is rejected.
        return false;
    }
}

