package com.test.���л�.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//UnicastRemoteObject���ڵ�����Զ�̶���ͻ�����Զ�̶���ͨ�ŵĴ����
public class ServiceImpl extends UnicastRemoteObject implements IService {

	protected ServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public String service(String content) throws RemoteException {
		return "server >> " + content;
	}

}
