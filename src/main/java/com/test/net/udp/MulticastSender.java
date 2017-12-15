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
		 * ��ttl���������������ݱ������Կ�����ٸ����磬
		 * ��ttl��ֵΪ0ʱ��ָ�����ݱ�Ӧͣ���ڱ���������
		 * ��ttl��ֵΪ1ʱ��ָ�����ݱ����͵����ؾ�������
		 * ��ttl��ֵΪ32ʱ����ζ��ֻ�ܷ��͵���վ��������ϣ�
		 * ��ttl��ֵΪ64ʱ����ζ�����ݱ�Ӧ�����ڱ�������
		 * ��ttl��ֵΪ128ʱ����ζ�����ݱ�Ӧ�����ڱ����ޣ�
		 * ��ttl��ֵΪ255ʱ����ζ�����ݱ��ɷ��͵����еط���
		 * ��Ĭ������£���ttl��ֵΪ1
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
