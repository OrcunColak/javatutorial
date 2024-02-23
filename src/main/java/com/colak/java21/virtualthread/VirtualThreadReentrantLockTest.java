package com.colak.java21.virtualthread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * See <a href="https://blog.stackademic.com/unveiling-the-power-of-virtual-threads-in-java-a-deep-dive-0c96fe50a3cb">...</a>
 */
@Slf4j
public class VirtualThreadReentrantLockTest {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Runnable task = () -> {
            lock.lock();
            try {
                log.info("acquired the lock.");

                // Simulate some work
                sleep();

                log.info("released the lock.");
            } finally {
                lock.unlock();
            }
        };

        Thread virtualThread1 = Thread.ofVirtual().name("virtual-1").start(task);
        Thread virtualThread2 = Thread.ofVirtual().name("virtual-2").start(task);

        virtualThread1.join();
        virtualThread2.join();
    }

    private static void sleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}
