package com.test.javahome;

import java.util.Map.Entry;

public class GetJavaHome {

    /**
     * 当程序中需要使用与操作系统相关的变量（例如：文件分隔符、换行符）时，Java提供了System类的静态方法getenv()和getProperty()用于返回系统相关的变量与属性，
     * getenv方法返回的变量大多于系统相关，
     * getProperty方法返回的变量大多与java程序有关。
     *
     * @param args
     */
    public static void main(String[] args) {
        String javaHome = System.getProperty("java.home");
        String arch = System.getProperty("sun.arch.data.model");
        System.out.println(System.getenv("java_home"));
        System.out.println(System.getenv("JAVA_HOME"));

        System.out.println(javaHome);
        System.out.println(arch);
        // getProperty方法返回的变量大多与java程序有关。
        for (Entry<Object, Object> entry : System.getProperties().entrySet()) {
            // System.out.println(entry.getKey() + "===========" + entry.getValue());
        }
        // getenv方法返回的变量大多于系统相关，
        for (Entry<String, String> entry : System.getenv().entrySet()) {
            // System.out.println(entry.getKey() + "===========" + entry.getValue());
        }
    }
}
