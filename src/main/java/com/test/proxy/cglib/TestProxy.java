package com.test.proxy.cglib;

import com.test.proxy.UserDao;

/**
 * 测试类
 */
public class TestProxy {
	public static void main(String[] args) {
		// 目标对象
		UserDao target = new UserDao();

		// 代理对象
		UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();

		// 执行代理对象的方法
		proxy.save();
		proxy.get();
	}
}
