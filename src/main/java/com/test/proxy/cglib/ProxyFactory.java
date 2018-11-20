package com.test.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyFactory implements MethodInterceptor {

    // 维护目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 给目标对象创建一个代理对象
    public Object getProxyInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("开始事务...");
        Object returnValue = method.invoke(target, args);
        System.out.println("提交事务...");
        return returnValue;
    }

}
