package com.test.���л�;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * https://www.ibm.com/developerworks/cn/java/j-lo-serial/
 * 
 * @author Administrator
 *
 */
public class SimpleSerial {

	/**
	 * ���Ƚ�һ��Person���󱣴浽�ļ�person.out�У�Ȼ���ٴӸ��ļ��ж������洢��Person���󣬲���ӡ�ö���
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		File file = new File("person.out");

		ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file));
		Person person = new Person("John", 101, Gender.MALE);
		oout.writeObject(person);
		oout.close();

		ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));
		Object newPerson = oin.readObject(); // û��ǿ��ת����Person����
		oin.close();
		System.out.println(newPerson);
	}
}
