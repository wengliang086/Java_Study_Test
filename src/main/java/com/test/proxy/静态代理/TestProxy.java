package com.test.proxy.��̬����;

import com.test.proxy.UserDao;

/**
 * ������
 */
public class TestProxy {
	public static void main(String[] args) {
		// Ŀ�����
		UserDao target = new UserDao();

		// �������,��Ŀ����󴫸��������,���������ϵ
		UserDaoProxy proxy = new UserDaoProxy(target);

		proxy.save();// ִ�е��Ǵ���ķ���
	}
}
