package com.test.���л�.rmi;

import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;

/* 
* Context�ӿڱ�ʾһ�����������ģ�����һ�����Ƶ�����İ���ɡ� 
* ���������͸�����Щ�󶨵�һЩ������ 
*/
/* 
* InitialContext����ִ�����������ĳ�ʼ�����ġ�    
* �ó�ʼ������ʵ�� Context �ӿڲ��ṩ�������Ƶ���ʼ�㡣 
*/
public class Server {

	public static void main(String[] args) {
		try {
			// ʵ����ʵ����IService�ӿڵ�Զ�̷���ServiceImpl����
			IService service = new ServiceImpl();
			// ��ʼ�������ռ�
			Context namingContext = new InitialContext();
			// �����ư󶨵�����,���������ռ�ע���Ѿ�ʵ������Զ�̷������
			namingContext.rebind("rmi://localhost/service02", service);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("��������������ע����1��Զ�̷������");
	}
}
