package com.colak.network.addresses;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

// List interfaces and their addresses
@Slf4j
@UtilityClass
class NetworkInterfaceList {

    public static void main() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            log.info("Interface Name: {}", networkInterface.getName());
            Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress address = addresses.nextElement();
                log.info("  Address: {}", address.getHostAddress());
            }
        }
    }
}
