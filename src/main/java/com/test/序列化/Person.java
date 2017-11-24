package com.test.序列化;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name = null;

	// 当某个字段被声明为transient后，默认序列化机制就会忽略该字段
	transient private Integer age = null;

	private Gender gender = null;

	public Person() {
		System.out.println("none-arg constructor");
	}

	public Person(String name, Integer age, Gender gender) {
		System.out.println("arg constructor");
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		System.out.println("writeObject invoked");
		out.defaultWriteObject();
		out.writeInt(age);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		System.out.println("readObject invoked");
		in.defaultReadObject();
		age = in.readInt();
	}

	private Object readResolve() throws ObjectStreamException {
		System.out.println("readResolve invoked");
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "[" + name + ", " + age + ", " + gender + "]";
	}
}