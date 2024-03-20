package com.colak.java21.virtualthread.percorescheduler;

import java.util.concurrent.BlockingQueue;

public class MyCarrierThread extends Thread {
    private final BlockingQueue<Runnable> queue;

    public MyCarrierThread(BlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Runnable runnable = queue.take();
                runnable.run();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}