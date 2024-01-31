package com.colak.rmi.calculator;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalculatorService extends Remote {
    int add(int a, int b) throws RemoteException;
    // Other remote methods...
}
