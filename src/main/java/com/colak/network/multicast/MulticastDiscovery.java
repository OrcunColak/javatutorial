package com.colak.network.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MulticastDiscovery {

    private static final String MULTICAST_GROUP_ADDRESS = "230.0.0.0"; // Multicast group address
    private static final int MULTICAST_PORT = 4446; // Port for multicast messages
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        // Start receiving messages
        executor.scheduleAtFixedRate(MulticastDiscovery::receiveMulticastMessages, 0, 1, TimeUnit.SECONDS);

        // Start sending discovery messages
        executor.scheduleAtFixedRate(MulticastDiscovery::sendMulticastMessage, 0, 5, TimeUnit.SECONDS);
    }

    // Method to send a multicast message
    private static void sendMulticastMessage() {
        try (MulticastSocket socket = new MulticastSocket()) {
            InetAddress group = InetAddress.getByName(MULTICAST_GROUP_ADDRESS);
            String message = "Hello, this is a discovery message from " + InetAddress.getLocalHost().getHostName();
            byte[] buffer = message.getBytes(StandardCharsets.UTF_8);

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, MULTICAST_PORT);
            socket.send(packet);

            System.out.println("Sent multicast message: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to receive multicast messages
    private static void receiveMulticastMessages() {
        byte[] buffer = new byte[BUFFER_SIZE];
        try (MulticastSocket socket = new MulticastSocket(MULTICAST_PORT)) {
            InetAddress group = InetAddress.getByName(MULTICAST_GROUP_ADDRESS);
            InetAddress localHost = InetAddress.getLocalHost(); // Get the address of the local machine

            socket.joinGroup(group);

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            // Check if the message is from the local machine
            if (!packet.getAddress().equals(localHost)) {
                String receivedMessage = new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8);
                System.out.println("Received multicast message: " + receivedMessage + " from " + packet.getAddress());
            } else {
                // Ignore message from self
                System.out.println("Received own multicast message, ignoring.");
            }

            socket.leaveGroup(group);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
