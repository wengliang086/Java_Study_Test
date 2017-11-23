package com.test.proxy.cglib;

/**
 * 目标对象,没有实现任何接口
 */
public class UserDao {
	public void save() {
		System.out.println("----已经保存数据!----");
	}

	public String get() {
		System.out.println("----获取数据!----");
		return "模拟数据";
	}
}
