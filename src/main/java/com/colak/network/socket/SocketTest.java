package com.colak.network.socket;

import com.colak.network.socket.executorservicesocket.ExecutorServiceSocketServer;
import com.colak.network.socket.singlethreadedsocket.SingleThreadedSocketServer;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class SocketTest {

    public static void main(String[] args) throws Exception {
        executorServiceSocketServerTest();
        singleThreadedSocketServerTest();

        System.exit(0);
    }

    private static void singleThreadedSocketServerTest() throws InterruptedException {
        SingleThreadedSocketServer socketServer = new SingleThreadedSocketServer();
        socketServer.start();
        // Wait for server to start
        TimeUnit.SECONDS.sleep(5);

        Client client = new Client();
        client.start();
        // Wait for processing
        TimeUnit.SECONDS.sleep(5);
        socketServer.stop();
    }

    private static void executorServiceSocketServerTest() throws InterruptedException {
        ExecutorServiceSocketServer socketServer = new ExecutorServiceSocketServer();
        socketServer.start();
        // Wait for server to start
        TimeUnit.SECONDS.sleep(5);

        Client client = new Client();
        client.start();
        // Wait for processing
        TimeUnit.SECONDS.sleep(5);
        socketServer.stop();
    }
}
