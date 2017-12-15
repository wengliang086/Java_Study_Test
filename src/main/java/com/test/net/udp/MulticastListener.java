package com.test.net.udp;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastListener {

	private int port;
	private String host;

	public MulticastListener(int port, String host) {
		super();
		this.port = port;
		this.host = host;
	}

	public void listen() throws Exception {
		byte[] data = new byte[1024];
		InetAddress address = InetAddress.getByName(host);
		MulticastSocket multicastSocket = new MulticastSocket(port);
		multicastSocket.joinGroup(address);
		DatagramPacket packet = new DatagramPacket(data, data.length);
		// receive()是阻塞方法，会等待客户端发送过来的信息
		multicastSocket.receive(packet);
		String message = new String(packet.getData(), 0, packet.getLength());
		System.out.println(message);
		multicastSocket.close();
	}

	public static void main(String[] args) throws Exception {
		int port = 1234;
		String host = "224.0.0.1";
		MulticastListener listener = new MulticastListener(port, host);
		while (true) {
			listener.listen();
		}
	}
}
