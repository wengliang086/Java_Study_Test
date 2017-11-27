package com.test.io;

import java.io.FileWriter;
import java.io.IOException;

public class TestCustomPrintStream {

	static final String path = System.getProperty("user.dir");

	public static void main(String[] args) throws IOException {
		System.out.println(path);
		System.err.println(path);
		// ¸²¸ÇÄ¬ÈÏÁ÷
		FileWriter fileWriter = new FileWriter(path + "/testLog.txt", true);
		System.setOut(new MyOutPrintStream(fileWriter));
		System.setErr(new MyErrPrintStream(fileWriter));

		System.out.println(path);
		System.err.println(path);
		// throw new NumberFormatException("zdfaf");
		try {
			Integer.parseInt("error int");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

}
