package com.test.net.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender {

	public static void main(String[] args) throws Exception {
		// �������ͷ����׽��֣�IPĬ��Ϊ���أ��˿ں����
		DatagramSocket sendSocket = new DatagramSocket();

		// ȷ��Ҫ���͵���Ϣ��
		String mes = "��ã����շ���";
		byte[] buf = mes.getBytes();
		int port = 8888;
		InetAddress address = InetAddress.getLocalHost();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
		// ͨ���׽��ַ������ݣ�
		sendSocket.send(packet);

		// ȷ�����ܷ������ݵĻ���洢�������洢���ݵ��ֽ�����
		byte[] getBuf = new byte[1024];

		// �����������͵����ݱ�
		DatagramPacket getPacket = new DatagramPacket(getBuf, getBuf.length);

		// ͨ���׽��ֽ�������
		sendSocket.receive(getPacket);

		// ������������Ϣ������ӡ
		String backMes = new String(getBuf, 0, getPacket.getLength());
		System.out.println("���ܷ����ص���Ϣ��" + backMes);

		// �ر��׽���
		sendSocket.close();
	}
}
