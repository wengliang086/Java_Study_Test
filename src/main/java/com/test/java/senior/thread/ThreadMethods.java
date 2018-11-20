package com.test.java.senior.thread;

public class ThreadMethods {

    public static void main(String[] args) throws Exception {
        System.out.println("start:" + System.currentTimeMillis());
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("子线程执行完成：" + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
        t1.join();
        System.out.println(System.currentTimeMillis());
    }
}
