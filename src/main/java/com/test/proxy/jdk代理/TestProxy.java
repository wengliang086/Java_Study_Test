package com.test.proxy.jdk����;

import com.test.proxy.IUserDao;
import com.test.proxy.UserDao;

/**
 * ������
 */
public class TestProxy {
	public static void main(String[] args) {
		// Ŀ�����
		UserDao target = new UserDao();
		// ��ԭʼ������ class ��
		System.out.println(target.getClass());

		// ��Ŀ����󣬴����������
		IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
		// class $Proxy0 �ڴ��ж�̬���ɵĴ������
		System.out.println(proxy.getClass());

		proxy.save();// ִ�е��Ǵ���ķ���
		String data = proxy.get();
		System.out.println(data);
	}
}
