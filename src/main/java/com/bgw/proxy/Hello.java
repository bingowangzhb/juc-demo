package com.bgw.proxy;

/**
 * desc：
 *
 * @author wangzhb 2019/8/12 18:13
 */
public class Hello implements IHello {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello, " + name);
    }
}
