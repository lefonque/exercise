package com.jpl.garage.examples.netty.base;

import java.nio.charset.Charset;

public class PipelineInfo {

	private String pipelineID;
	
	private Charset charset;
	private FrameType frameType;
	
	private int maxFrameLength;
	private int lengthFieldOffset;
	private int lengthFieldLength;
	private int lengthAdjustment;
	private int initialBytesToStrip;
	
	private String delimit;

	public String getPipelineID() {
		return pipelineID;
	}

	public void setPipelineID(String pipelineID) {
		this.pipelineID = pipelineID;
	}

	public Charset getCharset() {
		return charset;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	public FrameType getFrameType() {
		return frameType;
	}

	public void setFrameType(FrameType frameType) {
		this.frameType = frameType;
	}

	public int getMaxFrameLength() {
		return maxFrameLength;
	}

	public void setMaxFrameLength(int maxFrameLength) {
		this.maxFrameLength = maxFrameLength;
	}

	public int getLengthFieldOffset() {
		return lengthFieldOffset;
	}

	public void setLengthFieldOffset(int lengthFieldOffset) {
		this.lengthFieldOffset = lengthFieldOffset;
	}

	public int getLengthFieldLength() {
		return lengthFieldLength;
	}

	public void setLengthFieldLength(int lengthFieldLength) {
		this.lengthFieldLength = lengthFieldLength;
	}

	public int getLengthAdjustment() {
		return lengthAdjustment;
	}

	public void setLengthAdjustment(int lengthAdjustment) {
		this.lengthAdjustment = lengthAdjustment;
	}

	public int getInitialBytesToStrip() {
		return initialBytesToStrip;
	}

	public void setInitialBytesToStrip(int initialBytesToStrip) {
		this.initialBytesToStrip = initialBytesToStrip;
	}

	public String getDelimit() {
		return delimit;
	}

	public void setDelimit(String delimit) {
		this.delimit = delimit;
	}
}
