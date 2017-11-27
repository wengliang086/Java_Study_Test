package com.test.javahome;

import java.util.Map.Entry;

public class GetJavaHome {

	/**
	 * ����������Ҫʹ�������ϵͳ��صı��������磺�ļ��ָ��������з���ʱ��Java�ṩ��System��ľ�̬����getenv()��getProperty()���ڷ���ϵͳ��صı��������ԣ� 
	 * getenv�������صı��������ϵͳ��أ� 
	 * getProperty�������صı��������java�����йء�
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String javaHome = System.getProperty("java.home");
		String arch = System.getProperty("sun.arch.data.model");
		System.out.println(System.getenv("java_home"));
		System.out.println(System.getenv("JAVA_HOME"));

		System.out.println(javaHome);
		System.out.println(arch);
		// getProperty�������صı��������java�����йء�
		for (Entry<Object, Object> entry : System.getProperties().entrySet()) {
			// System.out.println(entry.getKey() + "===========" + entry.getValue());
		}
		// getenv�������صı��������ϵͳ��أ�
		for (Entry<String, String> entry : System.getenv().entrySet()) {
			// System.out.println(entry.getKey() + "===========" + entry.getValue());
		}
	}
}
