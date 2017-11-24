package com.test.proxy.cglib;

import com.test.proxy.UserDao;

/**
 * ������
 */
public class TestProxy {
	public static void main(String[] args) {
		// Ŀ�����
		UserDao target = new UserDao();

		// �������
		UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();

		// ִ�д������ķ���
		proxy.save();
		proxy.get();
	}
}
