package com.test.net.nio;

import java.nio.IntBuffer;

public class TestBuffer {
	public static void main(String args[]) {
		IntBuffer intBuffer = IntBuffer.allocate(10);
		intBuffer.put(10);
		intBuffer.put(101);
		System.err.println("Write mode: ");
		System.err.println("\tCapacity: " + intBuffer.capacity());
		System.err.println("\tPosition: " + intBuffer.position());
		System.err.println("\tLimit: " + intBuffer.limit());// limit - position 表示此时还可以写入/读取多少单位的数据.

		intBuffer.flip();
		System.err.println("Read mode: ");
		System.err.println("\tCapacity: " + intBuffer.capacity());
		System.err.println("\tPosition: " + intBuffer.position());
		System.err.println("\tLimit: " + intBuffer.limit());
	}
}
