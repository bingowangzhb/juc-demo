package com.bgw.proxy;

/**
 * desc：
 *
 * @author wangzhb 2019/8/12 18:33
 */
public class CglibProxyTestCase {

    public static void main(String[] args) {
        Hello helloProxy = CglibProxy.getInstance().getProxy(Hello.class);
        helloProxy.sayHello("bgw");
    }
}
