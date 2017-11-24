package com.test.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * ��jar����META-INF/services/Ŀ¼��ͬʱ����һ���Է���ӿ��������ļ������ļ������ʵ�ָ÷���ӿڵľ���ʵ���ࡣ
 * 
 * @author Administrator jdk�ṩ����ʵ�ֲ��ҵ�һ�������ࣺjava.util.ServiceLoader
 */
public class SearchFactory {

	private SearchFactory() {
	}

	public static Search newSearch() {
		ServiceLoader<Search> serviceLoader = ServiceLoader.load(Search.class);
		Iterator<Search> searchs = serviceLoader.iterator();
		if (searchs.hasNext()) {
			return searchs.next();
		}
		return null;
	}
}
