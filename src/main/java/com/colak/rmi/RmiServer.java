package com.colak.rmi;

import com.colak.rmi.calculator.CalculatorService;
import com.colak.rmi.calculator.CalculatorServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

@Slf4j
public class RmiServer {
    public void start() {
        try {
            CalculatorService calculatorService = new CalculatorServiceImpl();

            // the remote object must be exported to the Java RMI runtime so that it may receive incoming remote calls.
            CalculatorService stub = (CalculatorService) UnicastRemoteObject.exportObject(calculatorService, 0);

            // Create a local registry
            Registry registry = LocateRegistry.createRegistry(1099);

            // bind our stub to the registry.
            Naming.rebind("CalculatorService", stub);

            log.info("RMI Server is running...");
        } catch (Exception exception) {
            log.error("Exception caught", exception);
        }
    }
}
