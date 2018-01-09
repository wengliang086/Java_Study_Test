package com.test.proxy.静态代理;

import com.test.proxy.UserDao;

/**
 * 测试类
 */
public class TestProxy {
	public static void main(String[] args) {
		// 目标对象
		UserDao target = new UserDao();

		// 代理对象,把目标对象传给代理对象,建立代理关系
		UserDaoProxy proxy = new UserDaoProxy(target);

		proxy.save();// 执行的是代理的方法
	}
}
