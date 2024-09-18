package com.colak.network.multicast;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@UtilityClass
class MulticastDiscoveryTest {

    private static final String MULTICAST_GROUP_ADDRESS = "230.0.0.0"; // Multicast group address
    private static final int MULTICAST_PORT = 4446; // Port for multicast messages
    private static final int BUFFER_SIZE = 1024;

    // Unique ID for the sender (e.g., could be UUID or hostname)
    private static final String SENDER_ID = UUID.randomUUID().toString();

    private static MulticastSocket socket;
    private static InetAddress group;
    private static InetSocketAddress multicastAddress;
    private static NetworkInterface networkInterface;

    public record DiscoveryMessage(String senderId, String messageContent) {
    }

    public static void main() throws IOException {

        createMulticastSocket();

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        // Start receiving messages
        executor.scheduleAtFixedRate(MulticastDiscoveryTest::receiveMulticastMessages, 0, 1, TimeUnit.SECONDS);

        // Start sending discovery messages
        executor.scheduleAtFixedRate(MulticastDiscoveryTest::sendMulticastMessage, 0, 5, TimeUnit.SECONDS);

        // Add shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(MulticastDiscoveryTest::shutdown));
    }

    private static void shutdown() {
        try {

            if (socket != null) {
                leaveGroup();
                socket.close();
            }
        } catch (IOException exception) {
            log.error("Exception during shutdown: ", exception);
        }
    }

    private static void leaveGroup() throws IOException {
        try {
            socket.leaveGroup(multicastAddress, networkInterface);
        } catch (SocketException exception) {
            log.error("Error leaving group: ", exception);
        }
    }

    private static void createMulticastSocket() throws IOException {
        socket = new MulticastSocket(MULTICAST_PORT);
        group = InetAddress.getByName(MULTICAST_GROUP_ADDRESS);

        // Get the network interface
        InetAddress byName = InetAddress.getByName("0.0.0.0");
        networkInterface = NetworkInterface.getByInetAddress(byName);
        multicastAddress = new InetSocketAddress(group, MULTICAST_PORT);
        // Join the multicast group
        socket.joinGroup(multicastAddress, networkInterface);
    }

    // Method to send a multicast message
    private static void sendMulticastMessage() {
        try {
            // Create a DiscoveryMessage object
            DiscoveryMessage message = new DiscoveryMessage(SENDER_ID, "Hello, this is a discovery message!");

            // Serialize the message object to a byte array
            byte[] buffer = serialize(message);

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, MULTICAST_PORT);
            socket.send(packet);

            log.info("Sent multicast message: {}", message);
        } catch (IOException exception) {
            log.error("Exception during sendMulticastMessage: ", exception);
        }
    }

    // Method to receive multicast messages
    private static void receiveMulticastMessages() {
        try {
            byte[] buffer = new byte[BUFFER_SIZE];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            // Deserialize the byte array into a DiscoveryMessage object
            DiscoveryMessage receivedMessage = deserialize(packet.getData());

            // Ignore message from self
            if (!SENDER_ID.equals(receivedMessage.senderId())) {
                log.info("Received multicast message: {}", receivedMessage);
            }
        } catch (IOException exception) {
            log.error("Exception during receiveMulticastMessages: ", exception);
        }
    }

    // Helper method to serialize an object to a byte array
    private static byte[] serialize(DiscoveryMessage message) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try (DataOutputStream dataStream = new DataOutputStream(byteStream)) {
            // Write senderId
            dataStream.writeUTF(message.senderId());

            // Write messageContent
            dataStream.writeUTF(message.messageContent());
        }
        return byteStream.toByteArray();
    }

    // Helper method to deserialize a byte array back into a DiscoveryMessage object
    private static DiscoveryMessage deserialize(byte[] bytes) throws IOException {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
        try (DataInputStream dataStream = new DataInputStream(byteStream)) {
            // Read senderId
            String senderId = dataStream.readUTF();

            // Read messageContent
            String messageContent = dataStream.readUTF();

            return new DiscoveryMessage(senderId, messageContent);
        }
    }
}
