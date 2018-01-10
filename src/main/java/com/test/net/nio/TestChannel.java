package com.test.net.nio;

import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author Administrator
 * 
 * 通常来说, 所有的 NIO 的 I/O 操作都是从 Channel 开始的. 一个 channel 类似于一个 stream。
 * java Stream 和 NIO Channel 对比：
 * (1) 我们可以在同一个 Channel 中执行读和写操作, 然而同一个 Stream 仅仅支持读或写.
 * (2) Channel 可以异步地读写, 而 Stream 是阻塞的同步读写.
 * (3) Channel 总是从 Buffer 中读取数据, 或将数据写入到 Buffer 中.
 * 
 * Channel 类型有:
 * (1) FileChannel, 文件操作
 * (2) DatagramChannel, UDP 操作
 * (3) SocketChannel, TCP 操作
 * (4) ServerSocketChannel, TCP 操作, 使用在服务器端.
 *     这些通道涵盖了 UDP 和 TCP网络 IO以及文件 IO.
 */
public class TestChannel {

	public static void main(String[] args) throws Exception {
		TestChannel test = new TestChannel();
		test.fileChannel();
	}

	private void fileChannel() throws Exception {
		String filePath = "C:\\Users\\Administrator\\Desktop\\test.txt";
		RandomAccessFile accessFile = new RandomAccessFile(filePath, "rw");
		FileChannel fChannel = accessFile.getChannel();

		// 读文件
		ByteBuffer buf = ByteBuffer.allocate(64);
		int bytesRead = fChannel.read(buf);
		while (bytesRead != -1) {
			buf.flip();
			while (buf.hasRemaining()) {
				System.out.print((char) buf.get());
			}
			buf.clear();
			bytesRead = fChannel.read(buf);
		}
		// 写文件
		String newData = "New String to write to file..." + System.currentTimeMillis();
		buf.clear();
		buf.put(newData.getBytes());
		buf.flip();
		while (buf.hasRemaining()) {
			fChannel.write(buf);
		}
		fChannel.close();
		accessFile.close();
	}

	private void socketChannel() throws Exception {
		SocketChannel socketChannel = SocketChannel.open();
		int port = 80;
		socketChannel.connect(new InetSocketAddress(port));
		socketChannel.close();
	}
}
