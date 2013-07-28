package com.jpl.garage.examples.netty.rmi;

import java.rmi.RemoteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpl.garage.examples.netty.client.ConnectedOutboundClient;

@Service("RMIService")
public class RMIService implements IRMIService {

	@Autowired
	private ConnectedOutboundClient outClient;
	
	@Override
	public void sendMessage(String message) throws RemoteException {
		outClient.writeMessage(message);
	}
}
