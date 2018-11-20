package com.test.java.senior.future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 假设有一个很耗时的返回值需要计算，并且这个返回值不是立刻需要的话， 那么就可以使用这个组合，
 * 用另一个线程去计算返回值，而当前线程在使用这个返回值之前可以做其它的操作，等到需要这个返回值时，再通过Future得到
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

        Thread.sleep(1000);// 可能做一些事情
        Integer r = futureTask.get(10, TimeUnit.SECONDS);
        System.out.println(r);
        /** 第二种方式 */
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<Integer> future = threadPool.submit(callable);
        Thread.sleep(1000);// 可能做一些事情
        System.out.println(future.get());
        threadPool.shutdown();
    }
}
