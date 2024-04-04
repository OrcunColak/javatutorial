package com.colak.java21.virtualthread.platform;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadFactory;

/**
 * See <a href="https://bohutskyi.com/virtual-threads-in-java-21-dcf34ca7c37d">...</a>
 */
@Slf4j
class PlatformThreadTest {

    public static void main(String[] args) {

        Runnable runnable = () -> log.info("Thread started");
        // Start a daemon thread to run a task
        Thread thread1 = Thread.ofPlatform().daemon().start(runnable);

        // Create an unstarted thread with name "duke", its start() method
        // must be invoked to schedule it to execute.
        Thread thread2 = Thread.ofPlatform().name("duke").unstarted(runnable);
        thread2.start();

        // A ThreadFactory that creates daemon threads named "worker-0", "worker-1", ...
        ThreadFactory factory = Thread.ofPlatform().daemon().name("worker-", 0).factory();
        Thread thread3 = factory.newThread(runnable);
        thread3.start();
    }
}
