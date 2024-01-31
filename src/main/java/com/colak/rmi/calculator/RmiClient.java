package com.colak.rmi.calculator;

import lombok.extern.slf4j.Slf4j;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@Slf4j
public class RmiClient {

    public void start() {
        try {
            Registry registry = LocateRegistry.getRegistry();
            CalculatorService calculator = (CalculatorService) registry.lookup("CalculatorService");
            int result = calculator.add(5, 10);

            log.info("Result from RMI server: " + result);
        } catch (Exception exception) {
            log.error("Exception caught", exception);
        }
    }
}
