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
 * ������
 */
public class TestProxy {
	public static void main(String[] args) throws Exception {
		createProxy();
	}

	/**
	 * �ֶ������ֽ���
	 * 
	 * @throws Exception
	 */
	private static void createProxy() throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.makeClass("com.test.proxy.javassist.UserDaoProxy");

		// ���ýӿ�
		CtClass inter = pool.get("com.test.proxy.IUserDao");
		cc.setInterfaces(new CtClass[] { inter });

		// ����Field
		CtField field = CtField.make("private com.test.proxy.IUserDao target;", cc);
		cc.addField(field);

		CtClass udc = pool.get("com.test.proxy.IUserDao");
		CtClass[] arrays = new CtClass[] { udc };
		CtConstructor ctc = CtNewConstructor.make(arrays, null, CtNewConstructor.PASS_NONE, null, null, cc);
		// ���ù��캯���ڲ���Ϣ
		ctc.setBody("{this.target=$1;}");
		cc.addConstructor(ctc);

		// save ����
		CtMethod saveM = CtMethod.make("public void save() {}", cc);
		saveM.setBody("{System.out.println(\"javassist��ʼ����...\");" + "target.save();" + "System.out.println(\"javassist�ύ����...\");}");
		cc.addMethod(saveM);

		// get ����
		CtMethod getM = CtMethod.make("public java.lang.String get() {System.out.println(\"javassist get...\");return target.get();}", cc);
		cc.addMethod(getM);

		// ��ȡ��̬���ɵ�class
		Class<?> c = cc.toClass();
		// ��ȡ������
		Constructor<?> constructor = c.getConstructor(IUserDao.class);
		// ͨ��������ʵ����
		IUserDao o = (IUserDao) constructor.newInstance(new UserDao());
		o.save();
		o.get();

		String path = "C:\\Users\\Administrator\\Desktop/";
		cc.writeFile(path);
	}
}
