package com.colak.network.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Slf4j
public class Client {

    private static final int PORT = 12345;

    public void start() {
        try (Socket socket = new Socket("localhost", PORT);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

            User user = new User("john_doe");
            out.writeObject(user);
            log.info("User object sent to server");

        } catch (IOException exception) {
            log.error("Exception caught ", exception);
        }
    }
}
