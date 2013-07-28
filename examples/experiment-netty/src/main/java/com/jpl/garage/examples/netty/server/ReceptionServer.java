package com.jpl.garage.examples.netty.server;

import java.nio.charset.Charset;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.math.NumberUtils;
import org.jboss.netty.bootstrap.Bootstrap;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.jpl.garage.examples.netty.base.CSType;
import com.jpl.garage.examples.netty.base.ConnectionInfo;
import com.jpl.garage.examples.netty.base.FrameType;
import com.jpl.garage.examples.netty.base.PipelineInfo;
import com.jpl.garage.examples.netty.base.UnitFactory;

@Component
public class ReceptionServer {


	@Autowired
	@Qualifier("infoProp")
	private Properties infoProp;
	
	@Autowired
	private UnitFactory unitFactory;
	
	private AtomicLong recvCount;
	private ChannelGroup sendChannelGroup;
	
	@PostConstruct
	public void init(){
		this.recvCount = new AtomicLong();
	}
	
	public void start(){
		
		ConnectionInfo connInfo = new ConnectionInfo();
		connInfo.setPort(NumberUtils.toInt(infoProp.getProperty("server.recv.port")));
		connInfo.setCsType(CSType.valueOf("SERVER"));
		
		final PipelineInfo pipelineInfo = new PipelineInfo();
		pipelineInfo.setCharset(Charset.forName("UTF-8"));
		pipelineInfo.setFrameType(FrameType.valueOf("LENGTH_STR_FIELD"));
		pipelineInfo.setInitialBytesToStrip(0);
		pipelineInfo.setLengthAdjustment(0);
		pipelineInfo.setLengthFieldLength(4);
		pipelineInfo.setLengthFieldOffset(0);
		pipelineInfo.setMaxFrameLength(2000);
		
		Executor bossExecutor = Executors.newCachedThreadPool();
		Executor workerExecutor = Executors.newCachedThreadPool();
		ChannelFactory factory = new NioServerSocketChannelFactory(
				bossExecutor, workerExecutor);
		
		Bootstrap bootstrap = unitFactory.getBootstrap(factory, connInfo);
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = unitFactory.getPipeline(pipelineInfo);
				pipeline.addLast("customHandler", new ReceptionServerHandler(recvCount, sendChannelGroup));
				return pipeline;
			}
		});
		
		((ServerBootstrap)bootstrap).bind();
	}
	

	public void setSendChannelGroup(ChannelGroup sendChannelGroup) {
		this.sendChannelGroup = sendChannelGroup;
	}

}
