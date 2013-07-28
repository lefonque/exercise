package com.jpl.garage.examples.netty.client;

import java.nio.charset.Charset;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang.math.NumberUtils;
import org.jboss.netty.bootstrap.Bootstrap;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.execution.ExecutionHandler;
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.jpl.garage.examples.netty.base.CSType;
import com.jpl.garage.examples.netty.base.ConnectionInfo;
import com.jpl.garage.examples.netty.base.FrameType;
import com.jpl.garage.examples.netty.base.PipelineInfo;
import com.jpl.garage.examples.netty.base.UnitFactory;

@Component
public class ConnectedInboudClient {

	private Timer timer;

	@Autowired
	@Qualifier("infoProp")
	private Properties infoProp;

	@Autowired
	private UnitFactory unitFactory;
	
//	private Channel channel;
	private AtomicLong recvCount;
	
	public ConnectedInboudClient(){
		
		timer = new HashedWheelTimer();
		recvCount = new AtomicLong();
	}
	
	public void start(){
		Executor bossExecutor = Executors.newCachedThreadPool();
		Executor workerExecutor = Executors.newCachedThreadPool();
		ChannelFactory factory = new NioClientSocketChannelFactory(
				bossExecutor, workerExecutor,
				NumberUtils.toInt(infoProp.getProperty("client.inbound.worker.count")));
		
		ConnectionInfo connInfo = new ConnectionInfo();
		connInfo.setHost(infoProp.getProperty("client.inbound.ip"));
		connInfo.setPort(NumberUtils.toInt(infoProp.getProperty("client.inbound.port")));
		connInfo.setCsType(CSType.valueOf("CLIENT"));
		
		final PipelineInfo pipelineInfo = new PipelineInfo();
		pipelineInfo.setCharset(Charset.forName("UTF-8"));
		pipelineInfo.setFrameType(FrameType.valueOf("LENGTH_STR_FIELD"));
		pipelineInfo.setInitialBytesToStrip(0);
		pipelineInfo.setLengthAdjustment(0);
		pipelineInfo.setLengthFieldLength(4);
		pipelineInfo.setLengthFieldOffset(0);
		pipelineInfo.setMaxFrameLength(2000);
		
		OrderedMemoryAwareThreadPoolExecutor executor
			= new OrderedMemoryAwareThreadPoolExecutor(
					NumberUtils.toInt(infoProp.getProperty("client.inbound.omatp.pool.size")),
					NumberUtils.toInt(infoProp.getProperty("client.inbound.omatp.threshold.memory.channel")),
					NumberUtils.toInt(infoProp.getProperty("client.inbound.omatp.threshold.memory.total")));
		final ExecutionHandler execHandler = new ExecutionHandler(executor);
		
		Bootstrap bootstrap = unitFactory.getBootstrap(factory, connInfo);
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = unitFactory.getPipeline(pipelineInfo);
				pipeline.addLast("execHandler", execHandler);
				pipeline.addLast("customHandler", new InboundClientHandler(timer,infoProp,recvCount));
				return pipeline;
			}
		});
		
		((ClientBootstrap)bootstrap).connect();
		
//		ChannelFuture cf = ((ClientBootstrap)bootstrap).connect();
//		cf.addListener(new ChannelFutureListener() {
//			
//			@Override
//			public void operationComplete(ChannelFuture future) throws Exception {
//				if(future.isSuccess()){
//					channel = future.getChannel();
//				}
//			}
//		});
	}
}
