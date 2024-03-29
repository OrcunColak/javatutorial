package com.colak.network.socket.singlethreadedsocket;

import com.colak.network.socket.User;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class SingleThreadedSocketServer implements AutoCloseable {
    private static final int PORT = 12345;
    private volatile ServerSocket serverSocket;

    public void start() {
        // Server runs in its own thread
        Thread serverThread = new Thread(() -> {
            try {
                serverSocket = new ServerSocket(PORT);
                while (!serverSocket.isClosed()) {
                    // Wait for the next connection from a client.
                    Socket client = serverSocket.accept();
                    handleClient(client);
                }
            } catch (IOException _) {
            }
        });
        serverThread.start();
        log.info("Server started");
    }

    @Override
    public void close() {
        try {
            // This will interrupt the blocking accept() call
            serverSocket.close();
        } catch (IOException _) {
        }
        log.info("Server stopped...");
    }

    /**
     * Read the client InputStream and then close it.
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
