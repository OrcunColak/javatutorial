package com.colak.network.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
class Server {

    public void start() {
        // Server runs in its own thread
        Thread serverThread = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(12345);
                 Socket clientSocket = serverSocket.accept()) {
                processClient(clientSocket);
            } catch (IOException exception) {
                log.error("Exception caught ", exception);
            }
        });
        serverThread.start();
        log.info("Server started");
    }

    /**
     * Read the client InputStream and then close it
     */
    private void processClient(Socket clientSocket) {
        try (clientSocket;
             ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
            User receivedUser = (User) in.readObject();
            log.info("Received User object: " + receivedUser);
        } catch (IOException | ClassNotFoundException exception) {
            log.error("Exception caught ", exception);
        }
    }
}
