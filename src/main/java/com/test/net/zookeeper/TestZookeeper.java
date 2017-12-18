package com.test.net.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class TestZookeeper {

	private static int sessionTimeout = 1000;

	public static void main(String[] args) throws Exception {
		String connectString = "localhost:" + 2181;
		// ����һ���������������
		ZooKeeper zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
			// ������б��������¼�
			public void process(WatchedEvent event) {
				System.out.println("�Ѿ�������" + event.getType() + "�¼���");
			}
		});
		Stat stat = zk.exists("/testRootPath", true);
		if (stat != null) {
			System.out.println("/testRootPath Ŀ¼�ڵ��Ѿ����ڣ�");
			zk.delete("/testRootPath/testChildPathOne", -1);
			// zk.delete("/testRootPath/testChildPathTwo", -1);
			zk.delete("/testRootPath", -1);
		}
		// ����һ��Ŀ¼�ڵ�
		zk.create("/testRootPath", "testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		// ����һ����Ŀ¼�ڵ�
		zk.create("/testRootPath/testChildPathOne", "childOneData".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		// ȡ����Ŀ¼�ڵ��б�
		System.out.println(new String(zk.getData("/testRootPath", false, null)));
		// �޸���Ŀ¼�ڵ�����
		zk.setData("/testRootPath/testChildPathOne", "modifyChildDataOne".getBytes(), -1);
		System.out.println("Ŀ¼�ڵ�״̬��[" + zk.exists("/testRootPath", true) + "]");
		// ��������һ����Ŀ¼�ڵ�
		zk.create("/testRootPath/testChildPathTwo", "childTwoData".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo", true, null)));
		// ɾ����Ŀ¼�ڵ�
		zk.delete("/testRootPath/testChildPathTwo", -1);
		zk.delete("/testRootPath/testChildPathOne", -1);
		// ɾ����Ŀ¼�ڵ�
		zk.delete("/testRootPath", -1);
		// �ر�����
		zk.close();
	}
}
