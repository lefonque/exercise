package com.jpl.garage.cxf;

import org.apache.cxf.tools.wsdlto.WSDLToJava;

public class ServerGen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("서버 모듈 생성 Start !!!!");
		WSDLToJava.main(new String[]{
				"-server",
				"-d",
				"src/main/java",
				"src/main/resources/wsdls/Obsp.wsdl"
		});
		System.out.println("서버 모듈 생성 End !!!!");
	}

}
