package com.colak.rmi;

import com.colak.rmi.calculator.RmiClient;

import java.util.concurrent.CountDownLatch;

public class RmiTest {


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread serverThread = new Thread(() -> {
            RmiServer rmiServer = new RmiServer();
            rmiServer.start();
            countDownLatch.countDown();
        });
        serverThread.start();

        countDownLatch.await();
        RmiClient rmiClient = new RmiClient();
        rmiClient.start();

        System.exit(0);

    }
}
