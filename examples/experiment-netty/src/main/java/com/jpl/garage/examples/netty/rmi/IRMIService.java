package com.jpl.garage.examples.netty.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRMIService extends Remote {
	public void sendMessage(String message) throws RemoteException;
}
