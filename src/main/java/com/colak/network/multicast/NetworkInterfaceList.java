package com.colak.network.multicast;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetworkInterfaceList {

    public static void main() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            System.out.println("Interface: " + networkInterface.getName());
            Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress address = addresses.nextElement();
                System.out.println("  Address: " + address.getHostAddress());
            }
        }
    }
}
