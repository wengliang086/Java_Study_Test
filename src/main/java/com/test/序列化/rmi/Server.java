package com.test.���л�.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

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

	static int type = 1;

	public static void main(String[] args) {
		try {
			// ʵ����ʵ����IService�ӿڵ�Զ�̷���ServiceImpl����
			IService service = new ServiceImpl();
			// ����Զ�̶���ע���Registry��ʵ������ָ���˿�Ϊ8888��Ĭ�϶˿���1099��
			LocateRegistry.createRegistry(8888);
			if (type == 1) {
				// ��ʼ�������ռ�
				Context namingContext = new InitialContext();
				// �����ư󶨵�����,���������ռ�ע���Ѿ�ʵ������Զ�̷������
				namingContext.rebind("rmi://localhost:8888/service02", service);
			} else {
				// ��Զ�̶���ע�ᵽRMIע��������ϣ�������ΪRHello
				// �󶨵�URL��׼��ʽΪ��rmi://host:port/name(Э��������ʡ�ԣ���������д�������ԣ�
				Naming.bind("rmi://127.0.0.1:8888/service02", service);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("��������������ע����1��Զ�̷������");
	}
}
