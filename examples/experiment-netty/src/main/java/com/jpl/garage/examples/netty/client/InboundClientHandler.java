package com.jpl.garage.examples.netty.client;

import java.util.Properties;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang.math.NumberUtils;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.util.Timeout;
import org.jboss.netty.util.Timer;
import org.jboss.netty.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InboundClientHandler extends SimpleChannelHandler {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private AtomicLong counter;
	
	private Timer timer;

	private Properties infoProp;
	
	private Semaphore semaphore;
	
	public InboundClientHandler(Timer timer,Properties infoProp,AtomicLong recvCount){
		this.timer = timer;
		this.infoProp = infoProp;
		this.counter = recvCount;
		semaphore = new Semaphore(1);
	}
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		log.debug("Count Received Telegram : [{}]",counter.incrementAndGet());
		String message = (String)e.getMessage();
		Channel channel = e.getChannel();
		channel.write(generateAck(message));
		//OSB처리임을 가정하는 상황재현
		semaphore.acquire();
		timer.newTimeout(new TimerTask() {
			
			@Override
			public void run(Timeout timeout) throws Exception {
				log.debug("Delayed!!");
				semaphore.release();
			}
		}, NumberUtils.toLong(infoProp.get("delay.time").toString())
		, TimeUnit.MILLISECONDS);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		log.error("ERROR : [{}]",e);
		if(semaphore.hasQueuedThreads()){
			semaphore.release();
		}
		super.exceptionCaught(ctx, e);
	}

	private String generateAck(String message){
		byte[] resultBytes = message.getBytes();
		byte[] replace = "OK!".getBytes();
		System.arraycopy(replace, 0, resultBytes, 64-replace.length, replace.length);
		replace = "0060".getBytes();
		System.arraycopy(replace, 0, resultBytes, 0, replace.length);
		
		String result = new String(resultBytes,0,64);
		log.debug("generateAck bytes : [{}]",result.getBytes().length);
		return result;
	}
	
}
