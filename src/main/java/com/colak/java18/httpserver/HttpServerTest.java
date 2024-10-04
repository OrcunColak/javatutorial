package com.colak.java18.httpserver;

import com.sun.net.httpserver.SimpleFileServer;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.file.Path;


// https://rathod-ajay.medium.com/a-comprehensive-journey-from-java-8-to-java-21-with-code-examples-of-essential-api-enhancements-6817d2ab3ba8
@Slf4j
public class HttpServerTest {

    // http://localhost:8080
    public static void main(String[] args) throws Exception {
        int port = 8080;
        var addr = new InetSocketAddress(port);
        Path rootDirectory = Path.of(".").toAbsolutePath();
        log.info("Server started on port " + port);
        var server = SimpleFileServer.createFileServer(addr, rootDirectory, SimpleFileServer.OutputLevel.INFO);


        server.start();
        log.info("Server started on port " + port);
    }
}
