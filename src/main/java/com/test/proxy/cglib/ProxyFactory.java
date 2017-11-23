package com.test.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ProxyFactory implements MethodInterceptor {

	// ά��Ŀ�����
	private Object target;

	public ProxyFactory(Object target) {
		this.target = target;
	}

	// ��Ŀ����󴴽�һ���������
	public Object getProxyInstance() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(target.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
	}

	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("��ʼ����...");
		Object returnValue = method.invoke(target, args);
		System.out.println("�ύ����...");
		return returnValue;
	}

}
