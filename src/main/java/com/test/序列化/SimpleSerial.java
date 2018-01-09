package com.test.序列化;

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
	 * 它先将一个Person对象保存到文件person.out中，然后再从该文件中读出被存储的Person对象，并打印该对象。
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
		Object newPerson = oin.readObject(); // 没有强制转换到Person类型
		oin.close();
		System.out.println(newPerson);
	}
}
