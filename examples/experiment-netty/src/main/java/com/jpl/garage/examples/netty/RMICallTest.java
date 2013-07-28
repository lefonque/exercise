package com.jpl.garage.examples.netty;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpl.garage.examples.netty.rmi.IRMIService;

public class RMICallTest {

	private static final Logger log = LoggerFactory.getLogger(RMICallTest.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Registry registry = null;
		try {
			registry = LocateRegistry.getRegistry(3456);
			Object obj = registry.lookup("RMIService");
			IRMIService service = (IRMIService)obj;
			String str = "0420가나다라마바사아자차ABCDEFGHIZKL12345678!@#$%^&*()가나다라마바사아자차ABCDEFGHIZKL12345678!@#$%^&*()가나다라마바사아자차ABCDEFGHIZKL12345678!@#$%^&*()가나다라마바사아자차ABCDEFGHIZKL12345678!@#$%^&*()가나다라마바사아자차ABCDEFGHIZKL12345678!@#$%^&*()가나다라마바사아자차ABCDEFGHIZKL12345678!@#$%^&*()가나다라마바사아자차ABCDEFGHIZKL12345678!@#$%^&*()";
			
			log.debug("Size : [{}]",str.getBytes().length);
			service.sendMessage(str);
			for(int i = 0; i < 100; i++){
				service.sendMessage(str);				
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
