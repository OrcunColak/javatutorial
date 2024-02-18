package com.colak.concurrent.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ReentratnLockTest {

    private final ReentrantLock lock = new ReentrantLock();
    private int balance = 100;

    public static void main(String[] args) throws InterruptedException {
        ReentratnLockTest readWriteLockTest = new ReentratnLockTest();
        readWriteLockTest.testLock();
    }

    private void testLock() throws InterruptedException {
        Thread wifeThread = new Thread(() -> {
            for (int index = 0; index < 100; index++) {
                log.info("Withdraw : {}", withdraw(2));
            }
        });
        wifeThread.start();

        Thread husbandThread = new Thread(() -> {
            for (int index = 0; index < 100; index++) {
                log.info("Deposit : {}", deposit(1));
            }
        });
        husbandThread.start();

        husbandThread.join();
        wifeThread.join();
    }

    private int withdraw(int value) {
        // Acquiring the read lock
        lock.lock();
        try {
            if (balance >= value) {
                // Perform read operations on the shared resource
                balance = balance - value;
                return balance;
            } else {
                return balance;
            }
        } finally {
            // read lock released
            lock.unlock();
        }
    }

    public int deposit(int value) {
        // acquiring the write lock
        lock.lock();
        try {
            // Perform write operations on the shared resource
            balance += value;
            return balance;
        } finally {
            // write lock released
            lock.unlock();
        }
    }
}
