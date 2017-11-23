package com.test.proxy.��̬����;

import com.test.proxy.IUserDao;

/**
 * �������,��̬���� 
 * ȱ��:��Ϊ���������Ҫ��Ŀ�����ʵ��һ���Ľӿ�,���Ի��кܶ������,��̫��.ͬʱ,һ���ӿ����ӷ���,Ŀ�������������Ҫά��
 */
public class UserDaoProxy implements IUserDao {
	// ���ձ���Ŀ�����
	private IUserDao target;

	public UserDaoProxy(IUserDao target) {
		this.target = target;
	}

	public void save() {
		System.out.println("��ʼ����...");
		target.save();// ִ��Ŀ�����ķ���
		System.out.println("�ύ����...");
	}

	public String get() {
		return target.get();
	}
}
