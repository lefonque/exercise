package com.jpl.garage.examples.netty.base;

public class ConnectionInfo {

	private String host;
	private int port;
	private CSType csType;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public CSType getCsType() {
		return csType;
	}
	public void setCsType(CSType csType) {
		this.csType = csType;
	}
}
