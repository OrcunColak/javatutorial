package com.colak.network.multicast;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@Slf4j
@UtilityClass
class MulticastReceiverTest {

    private static final String MULTICAST_GROUP = "224.0.0.219"; // Multicast IP address
    private static final int MULTICAST_PORT = 53535; // Port to listen on

    public static void main() {
        receiveMulticast();
    }

    private static void receiveMulticast() {
        try (MulticastSocket multicastSocket = new MulticastSocket(MULTICAST_PORT)) {

            // Get the network interface
            InetAddress byName = InetAddress.getByName("0.0.0.0");
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(byName);

            // Join the multicast group
            InetAddress group = InetAddress.getByName(MULTICAST_GROUP);
            InetSocketAddress multicastAddress = new InetSocketAddress(group, MULTICAST_PORT);
            multicastSocket.joinGroup(multicastAddress, networkInterface);

            log.info("Listening for multicast messages on {}:{}", MULTICAST_GROUP, MULTICAST_PORT);

            // Buffer to receive incoming data
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            // Listen for messages
            while (true) {
                multicastSocket.receive(packet);
                // Wrap data from the packet as ByteBuffer
                byte[] receivedData = packet.getData();
                ByteBuffer byteBuffer = ByteBuffer.wrap(receivedData, 0, packet.getLength());

                // Read the data length
                int dataLength = byteBuffer.getInt();

                // Extract the data
                byte[] data = new byte[dataLength];
                byteBuffer.get(data);

                // Convert to String
                String message = new String(data, StandardCharsets.UTF_8);
                log.info("Received message: {}", message);
            }
        } catch (IOException exception) {
            log.error("Exception :", exception);
        }
    }
}
