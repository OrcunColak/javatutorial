package com.colak.network.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Slf4j
public class Client {

    public void start() {
        try (Socket socket = new Socket("localhost", 12345);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

            User user = new User("john_doe");
            out.writeObject(user);
            log.info("User object sent to server");

        } catch (IOException exception) {
            log.error("Exception caught ", exception);
        }
    }
}
