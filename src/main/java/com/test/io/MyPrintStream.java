package com.test.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

public class MyPrintStream extends PrintStream {

	static final String path = System.getProperty("user.dir");
	private static BufferedWriter bwriter;

	public static void main(String[] args) {
		System.out.println(path);
		// 覆盖默认流
		MyPrintStream printStream = new MyPrintStream();
		System.setOut(printStream);
		System.setErr(printStream);

		System.out.println(path);
		// throw new NumberFormatException("zdfaf");
		try {
			Integer.parseInt("error int");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	public MyPrintStream() {
		super(System.out);
		try {
			bwriter = new BufferedWriter(new FileWriter(path + "/testLog.txt", true));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void write(byte[] buf, int off, int len) {
		String message = new String(buf, off, len);
		if (!message.equals("\r\n"))
			message = "my:" + message;
		/**
		 * 默认位置输出
		 */
		super.write(message.getBytes(), off, message.length());
		// super.write(buf, off, len);
		/**
		 * 文件输出
		 */
		try {
			bwriter.write(message);
			bwriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
