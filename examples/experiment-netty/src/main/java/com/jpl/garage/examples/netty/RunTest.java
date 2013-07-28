package com.jpl.garage.examples.netty;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jpl.garage.examples.netty.client.ConnectedInboudClient;
import com.jpl.garage.examples.netty.client.ConnectedOutboundClient;
import com.jpl.garage.examples.netty.server.ReceptionServer;
import com.jpl.garage.examples.netty.server.TransmissionServer;

public class RunTest {

	public static void main(String[] args) {
		ApplicationContext context
			= new ClassPathXmlApplicationContext("classpath:/spring/spring-context.xml");
		
		TransmissionServer tServer = context.getBean(TransmissionServer.class);
		tServer.start();
		ReceptionServer rServer = context.getBean(ReceptionServer.class);
		rServer.setSendChannelGroup(tServer.getChannelGroup());
		rServer.start();
		
		context.getBean(ConnectedInboudClient.class).start();
		context.getBean(ConnectedOutboundClient.class).start();
	}

}
