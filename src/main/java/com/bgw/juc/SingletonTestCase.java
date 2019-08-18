package com.bgw.juc;

/**
 * desc：
 *
 * @author wangzhb 2019/8/5 22:49
 */
public class SingletonTestCase {
    private static volatile SingletonTestCase instance = null;

    private SingletonTestCase() {
        System.out.println("构造函数...");
    }

    // DCL double check lock 双端检锁机制
    public static SingletonTestCase getInstance() {
        if (null == instance) {
            synchronized (SingletonTestCase.class) {
                if (null == instance) {
                    instance = new SingletonTestCase();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 11; i++) {
            new Thread(SingletonTestCase::getInstance, "Thread-" + i).start();
        }
    }
}
