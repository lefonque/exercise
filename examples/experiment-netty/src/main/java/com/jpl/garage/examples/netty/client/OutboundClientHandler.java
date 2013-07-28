package com.jpl.garage.examples.netty.client;

import java.util.concurrent.atomic.AtomicLong;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.WriteCompletionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OutboundClientHandler extends SimpleChannelHandler {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private AtomicLong counter;
	
	public OutboundClientHandler(AtomicLong counter){
		this.counter = counter;
	}
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		super.messageReceived(ctx, e);
	}
	
	@Override
	public void writeComplete(ChannelHandlerContext ctx, WriteCompletionEvent e)
			throws Exception {
		log.debug("Count Telegram Sent : [{}]",counter.incrementAndGet());
		super.writeComplete(ctx, e);
	}
	
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		log.error("ERROR : [{}]",e);
	};
}
