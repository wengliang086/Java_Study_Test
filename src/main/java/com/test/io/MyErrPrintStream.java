package com.test.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

public class MyErrPrintStream extends PrintStream {

	private static BufferedWriter bwriter;
	private String prefix = "Err:";

	public MyErrPrintStream(FileWriter fileWriter) {
		super(System.err);
		bwriter = new BufferedWriter(fileWriter);
	}

	@Override
	public void write(byte[] buf, int off, int len) {
		String message = new String(buf, off, len);
		if (!message.equals("\r\n"))
			message = prefix + message;
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
