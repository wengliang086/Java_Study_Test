package com.test.proxy;

/**
 * �ӿ�ʵ�� Ŀ�����
 */
public class UserDao implements IUserDao {
	public void save() {
		System.out.println("----�Ѿ���������!----");
	}

	public String get() {
		System.out.println("----��ȡ����!----");
		return "ģ������";
	}
}
