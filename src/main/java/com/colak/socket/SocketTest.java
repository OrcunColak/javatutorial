package com.colak.socket;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SocketTest {

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.start();

        Client client = new Client();
        client.start();

        System.exit(0);
    }
}
