package com.test.序列化.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//UnicastRemoteObject用于导出的远程对象和获得与该远程对象通信的存根。
public class ServiceImpl extends UnicastRemoteObject implements IService {

	protected ServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public String service(String content) throws RemoteException {
		return "server >> " + content;
	}

}
