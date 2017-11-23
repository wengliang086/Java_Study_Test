package com.test.proxy.jdk代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 创建动态代理对象 动态代理不需要实现接口,但是需要指定接口类型
 */
public class ProxyFactory {

	// 维护一个目标对象
	private Object target;

	public ProxyFactory(Object target) {
		this.target = target;
	}

	// 给目标对象生成代理对象
	public Object getProxyInstance() {
		ClassLoader loader = target.getClass().getClassLoader();
		Class<?>[] interfaces = target.getClass().getInterfaces();
		Object t = Proxy.newProxyInstance(loader, interfaces, new InvocationHandler() {

			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("开始事务2 " + method.getName());
				Object ret = method.invoke(target, args);
				System.out.println("提交事务2");
				return ret;
			}
		});
		return t;
	}
}
