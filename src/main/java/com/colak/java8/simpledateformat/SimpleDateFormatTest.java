package com.colak.java8.simpledateformat;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * See <a href="https://medium.com/@luketong/are-you-still-using-simpledateformat-in-multi-thread-environment-36507d19917e">...</a>
 */
@Slf4j
public class SimpleDateFormatTest {

    private static final ThreadLocal<SimpleDateFormat> threadLocalDateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                Date currentDate = new Date();
                SimpleDateFormat dateFormat = threadLocalDateFormat.get();
                String formattedDate = dateFormat.format(currentDate);
                log.info(Thread.currentThread().getName() + ": " + formattedDate);
            }
            threadLocalDateFormat.remove();
        };

        // Create and start two threads running the same task
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
