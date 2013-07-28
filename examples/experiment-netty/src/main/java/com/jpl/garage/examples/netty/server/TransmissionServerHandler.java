package com.jpl.garage.examples.netty.server;

import java.util.concurrent.atomic.AtomicLong;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.WriteCompletionEvent;
import org.jboss.netty.channel.group.ChannelGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransmissionServerHandler extends SimpleChannelHandler {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private final AtomicLong counter;
	
	private final ChannelGroup channelGroup;
	
	public TransmissionServerHandler(AtomicLong counter,ChannelGroup channelGroup){
		this.counter = counter;
		this.channelGroup = channelGroup;
	}
	
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		channelGroup.add(e.getChannel());
		super.channelConnected(ctx, e);
	}
	
	@Override
	public void writeComplete(ChannelHandlerContext ctx, WriteCompletionEvent e)
			throws Exception {
		log.debug("wrote packet size : [{}]",e.getWrittenAmount());
		super.writeComplete(ctx, e);
	}
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		//=============================================================
		// Receive Ack
		//=============================================================
		log.debug("Count Received Ack : [{}]",counter.incrementAndGet());
		super.messageReceived(ctx, e);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		log.error("ERROR : [{}]",e);
		super.exceptionCaught(ctx, e);
	}
}
