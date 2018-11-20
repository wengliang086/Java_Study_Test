package com.test.proxy;

/**
 * 接口实现 目标对象
 */
public class UserDao implements IUserDao {
    public void save() {
        System.out.println("----已经保存数据!----");
    }

    public String get() {
        System.out.println("----获取数据!----");
        return "模拟数据";
    }
}
