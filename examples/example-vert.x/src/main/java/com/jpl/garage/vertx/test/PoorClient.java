package com.jpl.garage.vertx.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class PoorClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Socket socket = null;
		
		try {
			socket = new Socket("localhost",9876);
			OutputStream outStream = socket.getOutputStream();
			outStream.write("abcd".getBytes());
			outStream.flush();
			
			InputStream inStream = socket.getInputStream();
			byte[] buff = new byte[1024];
			inStream.read(buff);
			System.out.println(new String(buff).trim());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
