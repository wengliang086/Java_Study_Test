package com.test.proxy.jdk代理;

import java.io.FileOutputStream;
import java.io.IOException;

import com.test.proxy.IUserDao;
import com.test.proxy.UserDao;

import sun.misc.ProxyGenerator;

/**
 * 测试类
 */
public class TestProxy {
	public static void main(String[] args) {
		// 目标对象
		UserDao target = new UserDao();
		// 【原始的类型 class 】
		System.out.println(target.getClass());

		// 给目标对象，创建代理对象
		IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
		// class $Proxy0 内存中动态生成的代理对象
		System.out.println(proxy.getClass());

		proxy.save();// 执行的是代理的方法
		String data = proxy.get();
		System.out.println(data);

		genClass();
	}

	/**
	 * Jdk根据接口生成字节码，写入class文件，再反编译，就可以清晰的理解Jdk动态代理机制
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
