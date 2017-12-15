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
		/**
		 * 该ttl参数用于设置数据报最多可以跨过多少个网络，
		 * 当ttl的值为0时，指定数据报应停留在本地主机；
		 * 当ttl的值为1时，指定数据报发送到本地局域网；
		 * 当ttl的值为32时，意味着只能发送到本站点的网络上；
		 * 当ttl的值为64时，意味着数据报应保留在本地区；
		 * 当ttl的值为128时，意味着数据报应保留在本大洲；
		 * 当ttl的值为255时，意味着数据报可发送到所有地方；
		 * 在默认情况下，该ttl的值为1
		 */
		multicastSocket.setTimeToLive(1);
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
