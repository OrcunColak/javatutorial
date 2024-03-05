package com.colak.algorithms.ratelimiter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * See <a href="https://medium.com/@devenchan/implementing-rate-limiting-in-java-from-scratch-fixed-window-and-sliding-window-implementation-a6e8d6407d17">...</a>
 */
public class FixedWindowRateLimiter implements RateLimiter {
    private final int threshold;
    private final long windowUnit = 1000L;
    private volatile long windowStartTime;
    private final AtomicInteger counter = new AtomicInteger();
    private final Lock lock = new ReentrantLock();

    public FixedWindowRateLimiter(int threshold) {
        this.threshold = threshold;
        this.windowStartTime = System.currentTimeMillis();
    }

    @Override
    public boolean tryAcquire() {
        long currentTime = System.currentTimeMillis();

        lock.lock();
        try {
            if (currentTime - windowStartTime >= windowUnit) {
                counter.set(0);
                windowStartTime = currentTime;
            }
        } finally {
            lock.unlock();
        }

        return counter.incrementAndGet() <= threshold;
    }
}

