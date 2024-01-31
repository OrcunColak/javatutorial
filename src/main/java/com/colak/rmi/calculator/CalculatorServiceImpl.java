package com.colak.rmi.calculator;

import java.io.Serializable;
import java.rmi.RemoteException;

public class CalculatorServiceImpl implements CalculatorService, Serializable {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
    // Other method implementations...
}
