package com.jpl.garage.examples.netty.server;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicLong;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.group.ChannelGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReceptionServerHandler extends SimpleChannelHandler {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private AtomicLong counter;
	private ChannelGroup sendChannelGroup;
	
	public ReceptionServerHandler(AtomicLong recvCount, ChannelGroup sendChannelGroup){
		this.counter = recvCount;
		this.sendChannelGroup = sendChannelGroup;
	}
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		//=============================================================
		// Receive Telegram
		//=============================================================
		log.debug("Count Received Telegram : [{}]",counter.incrementAndGet());
		Channel ackChannel = e.getChannel();
		String message = (String)e.getMessage();
		ackChannel.write(generateAck(message));
		sendChannelGroup.write(generateResponseTelegram(message));
	}
	
	private String generateAck(String message){
		byte[] resultBytes = message.getBytes();
		byte[] replace = "OK!".getBytes();
		System.arraycopy(replace, 0, resultBytes, 64-replace.length, replace.length);
		replace = "0060".getBytes();
		System.arraycopy(replace, 0, resultBytes, 0, replace.length);
		byte[] tmp = new byte[64];
		System.arraycopy(resultBytes, 0, tmp, 0, 64);
		String result = null;
		try {
			result = new String(tmp,"UTF-8");
			log.debug("generateAck bytes : [{}]",result.getBytes("UTF-8").length);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	private String generateResponseTelegram(String message){
		byte[] resultBytes = message.getBytes();
		byte[] append = "===RESPONSE".getBytes();
		System.arraycopy(append, 0, resultBytes, resultBytes.length - append.length, append.length);
		log.debug("resultBytes's length : [{}]",resultBytes.length);
		String result = new String(resultBytes);
		return result;
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		log.error("ERROR : [{}]",e);
		super.exceptionCaught(ctx, e);
	}
}
