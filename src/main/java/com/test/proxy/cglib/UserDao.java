package com.test.proxy.cglib;

/**
 * Ŀ�����,û��ʵ���κνӿ�
 */
public class UserDao {
	public void save() {
		System.out.println("----�Ѿ���������!----");
	}

	public String get() {
		System.out.println("----��ȡ����!----");
		return "ģ������";
	}
}
