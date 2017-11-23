package com.test.proxy.jdk代理;

import com.test.proxy.IUserDao;
import com.test.proxy.UserDao;

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
	}
}
