package com.test.proxy.jdk����;

import java.io.FileOutputStream;
import java.io.IOException;

import com.test.proxy.IUserDao;
import com.test.proxy.UserDao;

import sun.misc.ProxyGenerator;

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

		genClass();
	}

	/**
	 * Jdk���ݽӿ������ֽ��룬д��class�ļ����ٷ����룬�Ϳ������������Jdk��̬�������
	 */
	private static void genClass() {
		String path = "C:\\Users\\Administrator\\Desktop/a.class";
		byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class<?>[] { IUserDao.class });
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(path);
			out.write(bytes);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
