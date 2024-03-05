package com.colak.algorithms.ratelimiter;

public interface RateLimiter {
    boolean tryAcquire();
}
