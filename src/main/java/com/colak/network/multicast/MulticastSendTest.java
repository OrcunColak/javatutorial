package com.colak.network.multicast;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

@UtilityClass
class MulticastSendTest {

    private static final String MULTICAST_GROUP = "224.0.0.219"; // Multicast IP address
    private static final int MULTICAST_PORT = 53535; // Port to listen on

    public static void main() throws IOException {
        sendJoinDatagram("test");
    }

    private static void sendJoinDatagram(String string) throws IOException {
        byte[] data = string.getBytes(StandardCharsets.UTF_8);

        try (MulticastSocket multicastSocket = new MulticastSocket(MULTICAST_PORT)) {

            // Get the network interface
            InetAddress byName = InetAddress.getByName("127.0.0.1");
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(byName);

            // Join the group
            InetAddress group = InetAddress.getByName(MULTICAST_GROUP);
            InetSocketAddress multicastAddress = new InetSocketAddress(group, MULTICAST_PORT);
            multicastSocket.joinGroup(multicastAddress, networkInterface);

            DatagramPacket packet = getDatagramPacket(data, group, MULTICAST_PORT);
            multicastSocket.send(packet);

            // Leave the group using the new method
            multicastSocket.leaveGroup(multicastAddress, networkInterface);
        }
    }

    private static DatagramPacket getDatagramPacket(byte[] data, InetAddress group, int multicastPort) {
        int msgSize = data.length;
        ByteBuffer byteBuffer = ByteBuffer.allocate(1 + 4 + msgSize);
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        byteBuffer.put(data);
        byte[] packetData = byteBuffer.array();

        return new DatagramPacket(packetData, packetData.length, group, multicastPort);
    }
}
