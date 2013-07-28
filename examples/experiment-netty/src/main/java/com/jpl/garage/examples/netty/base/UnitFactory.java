package com.jpl.garage.examples.netty.base;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.jboss.netty.bootstrap.Bootstrap;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;
import org.jboss.netty.handler.codec.frame.FixedLengthFrameDecoder;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.springframework.stereotype.Service;

@Service
public class UnitFactory {

	public Bootstrap getBootstrap(ChannelFactory factory, ConnectionInfo connInfo){
		
		Bootstrap bootstrap = null;
		SocketAddress address = null;
		
		if(connInfo.getCsType()==CSType.CLIENT){
			bootstrap = new ClientBootstrap();
			address = new InetSocketAddress(connInfo.getHost(), connInfo.getPort());
		}
		else{
			bootstrap = new ServerBootstrap();
			address = new InetSocketAddress(connInfo.getPort());
		}
		
		bootstrap.setFactory(factory);
		bootstrap.setOption(connInfo.getCsType().getAddressOption(), address);
		
		return bootstrap;
	}

	public ChannelPipeline getPipeline(PipelineInfo pipelineInfo){
		ChannelPipeline pipeline = Channels.pipeline();
		
		FrameDecoder frameDecoder = null;
		switch(pipelineInfo.getFrameType()){
		case FIXED_LENGTH:
			frameDecoder = new FixedLengthFrameDecoder(pipelineInfo.getMaxFrameLength());
			break;
			
		case LENGTH_NUM_FIELD:
			frameDecoder = new LengthFieldBasedFrameDecoder(
					pipelineInfo.getMaxFrameLength(),
					pipelineInfo.getLengthFieldOffset(),
					pipelineInfo.getLengthFieldLength(),
					pipelineInfo.getLengthAdjustment(),
					pipelineInfo.getInitialBytesToStrip());
			break;
			
		case LENGTH_STR_FIELD:
			frameDecoder = new LengthFieldStringFrameDecoder(
					pipelineInfo.getMaxFrameLength(),
					pipelineInfo.getLengthFieldOffset(),
					pipelineInfo.getLengthFieldLength(),
					pipelineInfo.getLengthAdjustment(),
					pipelineInfo.getInitialBytesToStrip());
			break;
			
		case LINE_DELIMIT:
			frameDecoder = new DelimiterBasedFrameDecoder(
					pipelineInfo.getMaxFrameLength(),Delimiters.lineDelimiter());
			break;
		}
		pipeline.addLast("frameDecoder", frameDecoder);
		
		//TODO have to implement later
		pipeline.addLast("stringDecoder", new StringDecoder(pipelineInfo.getCharset()));
		pipeline.addLast("stringEncoder", new StringEncoder(pipelineInfo.getCharset()));
//		pipeline.addLast("customHandler", new CustomHandler(timer,infoProp));
		return pipeline;
	}

}
