package com.test.java.senior.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * ������һ���ܺ�ʱ�ķ���ֵ��Ҫ���㣬�����������ֵ����������Ҫ�Ļ��� ��ô�Ϳ���ʹ�������ϣ�����һ���߳�ȥ���㷵��ֵ������ǰ�߳���ʹ���������ֵ֮ǰ�����������Ĳ������ȵ���Ҫ�������ֵʱ����ͨ��Future�õ�
 */
public class TestFuture {

	public static void main(String[] args) throws Exception {
		Callable<Integer> callable = new Callable<Integer>() {

			public Integer call() throws Exception {
				return new Random().nextInt(100);
			}
		};
		FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
		new Thread(futureTask).start();

		Thread.sleep(1000);// ������һЩ����
		Integer r = futureTask.get(10, TimeUnit.SECONDS);
		System.out.println(r);
		/** �ڶ��ַ�ʽ */
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		Future<Integer> future = threadPool.submit(callable);
		Thread.sleep(1000);// ������һЩ����
		System.out.println(future.get());
		threadPool.shutdown();
	}
}
