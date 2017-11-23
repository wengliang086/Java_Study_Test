package com.test.proxy.javassist;

import java.lang.reflect.Constructor;

import com.test.proxy.IUserDao;
import com.test.proxy.UserDao;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewConstructor;

/**
 * 测试类
 */
public class TestProxy {
	public static void main(String[] args) throws Exception {
		createProxy();
	}

	/**
	 * 手动创建字节码
	 * 
	 * @throws Exception
	 */
	private static void createProxy() throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.makeClass("com.test.proxy.javassist.UserDaoProxy");

		// 设置接口
		CtClass inter = pool.get("com.test.proxy.IUserDao");
		cc.setInterfaces(new CtClass[] { inter });

		// 设置Field
		CtField field = CtField.make("private com.test.proxy.IUserDao target;", cc);
		cc.addField(field);

		CtClass udc = pool.get("com.test.proxy.IUserDao");
		CtClass[] arrays = new CtClass[] { udc };
		CtConstructor ctc = CtNewConstructor.make(arrays, null, CtNewConstructor.PASS_NONE, null, null, cc);
		// 设置构造函数内部信息
		ctc.setBody("{this.target=$1;}");
		cc.addConstructor(ctc);

		// save 方法
		CtMethod saveM = CtMethod.make("public void save() {}", cc);
		saveM.setBody("{System.out.println(\"javassist开始事务...\");" + "target.save();" + "System.out.println(\"javassist提交事务...\");}");
		cc.addMethod(saveM);

		// get 方法
		CtMethod getM = CtMethod.make("public java.lang.String get() {System.out.println(\"javassist get...\");return target.get();}", cc);
		cc.addMethod(getM);

		// 获取动态生成的class
		Class<?> c = cc.toClass();
		// 获取构造器
		Constructor<?> constructor = c.getConstructor(IUserDao.class);
		// 通过构造器实例化
		IUserDao o = (IUserDao) constructor.newInstance(new UserDao());
		o.save();
		o.get();

		String path = "C:\\Users\\Administrator\\Desktop/";
		cc.writeFile(path);
	}
}
