package com.test.proxy.jdk����;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ������̬������� ��̬������Ҫʵ�ֽӿ�,������Ҫָ���ӿ�����
 */
public class ProxyFactory {

	// ά��һ��Ŀ�����
	private Object target;

	public ProxyFactory(Object target) {
		this.target = target;
	}

	// ��Ŀ��������ɴ������
	public Object getProxyInstance() {
		ClassLoader loader = target.getClass().getClassLoader();
		Class<?>[] interfaces = target.getClass().getInterfaces();
		Object t = Proxy.newProxyInstance(loader, interfaces, new InvocationHandler() {

			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("��ʼ����2 " + method.getName());
				Object ret = method.invoke(target, args);
				System.out.println("�ύ����2");
				return ret;
			}
		});
		return t;
	}
}
