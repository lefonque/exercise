package com.jpl.garage.examples.netty.client;

import java.nio.charset.Charset;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang.math.NumberUtils;
import org.jboss.netty.bootstrap.Bootstrap;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.jpl.garage.examples.netty.base.CSType;
import com.jpl.garage.examples.netty.base.ConnectionInfo;
import com.jpl.garage.examples.netty.base.FrameType;
import com.jpl.garage.examples.netty.base.PipelineInfo;
import com.jpl.garage.examples.netty.base.UnitFactory;

@Component
public class ConnectedOutboundClient {

//	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("infoProp")
	private Properties infoProp;
	
	@Autowired
	private UnitFactory unitFactory;
	
	private Channel channel;
	private AtomicLong recvCount;
	
	public ConnectedOutboundClient(){
		recvCount = new AtomicLong();
	}
	
	public void start(){

		ConnectionInfo connInfo = new ConnectionInfo();
		connInfo.setHost(infoProp.getProperty("client.outbound.ip"));
		connInfo.setPort(NumberUtils.toInt(infoProp.getProperty("client.outbound.port")));
		connInfo.setCsType(CSType.valueOf("CLIENT"));
		
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
		ChannelFactory factory = new NioClientSocketChannelFactory(
				bossExecutor, workerExecutor,
				NumberUtils.toInt(infoProp.getProperty("client.outbound.worker.count")));

		Bootstrap bootstrap = unitFactory.getBootstrap(factory, connInfo);
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = unitFactory.getPipeline(pipelineInfo);
				pipeline.addLast("customHandler", new OutboundClientHandler(recvCount));
				return pipeline;
			}
		});
		
		final CountDownLatch latch = new CountDownLatch(1);
		ChannelFuture cf = ((ClientBootstrap)bootstrap).connect();
		cf.addListener(new ChannelFutureListener() {
			
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				channel = future.getChannel();
				latch.countDown();
			}
		});
		
		try {
			latch.await(3*1000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String str = "0406가나다라마바사아자차ABCDEFGHIZKL12345678가나다라마바사아자차ABCDEFGHIZKL12345678가나다라마바사아자차ABCDEFGHIZKL12345678가나다라마바사아자차ABCDEFGHIZKL12345678가나다라마바사아자차ABCDEFGHIZKL12345678가나다라마바사아자차ABCDEFGHIZKL12345678가나다라마바사아자차ABCDEFGHIZKL12345678가나다라마바사아자차ABCDEFGHIZKL12345678======";
//		log.debug("Size : [{}]",str.getBytes().length);
//		channel.write(str);
	}
	
	public void writeMessage(String message){
		channel.write(message);
	}
}
