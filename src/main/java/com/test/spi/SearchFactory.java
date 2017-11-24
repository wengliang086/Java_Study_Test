package com.test.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 在jar包的META-INF/services/目录里同时创建一个以服务接口命名的文件。该文件里就是实现该服务接口的具体实现类。
 * 
 * @author Administrator jdk提供服务实现查找的一个工具类：java.util.ServiceLoader
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
