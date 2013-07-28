package com.jpl.garage.vertx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.net.NetSocket;
import org.vertx.java.core.streams.Pump;
import org.vertx.java.deploy.Verticle;


public class PoorServerVertical extends Verticle {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public void start() throws Exception {
		log.debug("Start Vertical!!!");
		vertx.createNetServer().connectHandler(new Handler<NetSocket>() {
			
			@Override
			public void handle(NetSocket socket) {
				log.debug("Start Anonymous Handler!!");
				Pump.createPump(socket, socket).start();
				
			}
		}).listen(9876);
		
	}

}
