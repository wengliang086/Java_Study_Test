package com.test.jmx;

public interface HelloWorldMBean {
    
    String getName();

    void setName(String name);

    void printHello();

    void printHello(String whoName);
}
