package com.test.io;

import java.io.*;

public class TestReadAndWriteFile {

    public static void main(String[] args) throws IOException {
        String inFileName = "C:\\Users\\Administrator\\Desktop\\test.txt";
        String outFileName = "";
        // 读文件
        FileInputStream fileInputStream = new FileInputStream(inFileName);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
        String str = dataInputStream.readLine();
        System.out.println(str);

        FileReader fileReader = new FileReader(inFileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        LineNumberReader lineNumberReader = new LineNumberReader(bufferedReader);
        String readLine = lineNumberReader.readLine();
        System.out.println(readLine);
    }
}
