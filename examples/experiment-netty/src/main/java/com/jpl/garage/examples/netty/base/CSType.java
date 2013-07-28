package com.jpl.garage.examples.netty.base;

public enum CSType {

	SERVER("localAddress"),
	CLIENT("remoteAddress"),
	;
	
	private String addressOption;
	
	CSType(String addressOption){
		this.addressOption = addressOption;
	}
	
	public String getAddressOption(){
		return addressOption;
	}
}
