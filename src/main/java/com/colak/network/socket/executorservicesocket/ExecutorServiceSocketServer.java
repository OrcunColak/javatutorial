package com.colak.network.socket.executorservicesocket;

import com.colak.network.socket.User;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ExecutorServiceSocketServer {

    private static final int PORT = 12345;
    private final ExecutorService executors = Executors.newFixedThreadPool(10);
    private volatile ServerSocket serverSocket;

    public void start() {
        // Server runs in its own thread
        Thread serverThread = new Thread(() -> {
            try {
                serverSocket = new ServerSocket(PORT);
                while (!serverSocket.isClosed()) {
                    // Wait for the next connection from a client.
                    Socket client = serverSocket.accept();
                    log.info("Accepted connection");
                    executors.submit(() -> handleClient(client));
                }
            } catch (IOException _) {
            }
        });
        serverThread.start();
        log.info("Server started");
    }

    public void stop() {
        try {
            serverSocket.close(); // This will interrupt the blocking accept() call
        } catch (IOException _) {
        }
        executors.shutdown();
        try {
            executors.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
        log.info("Server stopped...");
    }

    /**
     * Read the client InputStream and then close it
     */
    private void handleClient(Socket clientSocket) {
        try (clientSocket;
             ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
            User receivedUser = (User) in.readObject();
            log.info("Received User object: " + receivedUser);
        } catch (IOException | ClassNotFoundException exception) {
            log.error("Exception caught ", exception);
        }
    }
}
