package com.test.net.udp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastSender {

	private int port;
	private String host;
	private String data;

	public MulticastSender(String data, String host, int port) {
		this.data = data;
		this.host = host;
		this.port = port;
	}

	public void send() throws Exception {
		InetAddress address = InetAddress.getByName(host);
		MulticastSocket multicastSocket = new MulticastSocket();
		DatagramPacket p = new DatagramPacket(data.getBytes(), data.length());
		p.setAddress(address);
		p.setPort(port);
		multicastSocket.send(p);
		multicastSocket.close();
	}

	public static void main(String[] args) throws Exception {
		int port = 1234;
		String host = "224.0.0.1";
		String data = "hello world.";
		MulticastSender multicastSender = new MulticastSender(data, host, port);
		multicastSender.send();
	}
}
